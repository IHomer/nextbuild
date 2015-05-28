package nl.ihomer.nextbuild.backend.domain.configuration;

import nl.ihomer.nextbuild.backend.domain.ShoppingCart;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandTargetResolver;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandTargetResolver;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.annotation.SpringBeanParameterResolverFactory;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.*;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventhandling.replay.DiscardingIncomingMessageHandler;
import org.axonframework.eventhandling.replay.ReplayingCluster;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.jpa.JpaEventStore;
import org.axonframework.saga.spring.SpringResourceInjector;
import org.axonframework.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

/**
 * @author bvangameren
 *
 * Configuration class containing the general configuration of axon.
 */
@Configuration
public class AxonConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    SpringTransactionManager springTransactionManager(PlatformTransactionManager transactionManager){
        return new SpringTransactionManager(transactionManager);
    }

    @Bean
    public SpringResourceInjector springResourceInjector(){
        return new SpringResourceInjector();
    }

    @Bean
    public SpringBeanParameterResolverFactory springBeanParameterResolverFactory(){
        return new SpringBeanParameterResolverFactory();
    }

    @Bean
    public CommandBus commandBus(SpringTransactionManager springTransactionManager) {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.setTransactionManager(springTransactionManager);
        return commandBus;
    }

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        return new DefaultCommandGateway(commandBus);
    }

    @Bean
    public EventBus eventBus(ClusterSelector clusterSelector) {
        return new ClusteringEventBus(clusterSelector);
    }

    @Bean
    public ClusterSelector clusterSelector(Cluster replayingCluster){
        Map<String, Cluster> mapping = Collections.singletonMap("nl.ihomer.nextbuild.backend.report.handlers", replayingCluster);
        return new ClassNamePrefixClusterSelector(mapping, new SimpleCluster("Default"));
    }

    @Bean
    public ReplayingCluster replayingCluster(JpaEventStore eventStore, SpringTransactionManager springTransactionManager) {
        return new ReplayingCluster(
                new SimpleCluster("Replay"),
                eventStore,
                springTransactionManager,
                100,
                new DiscardingIncomingMessageHandler());
    }

    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor(EventBus eventBus) {
        AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
        processor.setEventBus(eventBus);
        return processor;
    }

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        ContainerManagedEntityManagerProvider provider = new ContainerManagedEntityManagerProvider();
        provider.setEntityManager(entityManager);
        return provider;
    }

    @Bean
    public JpaEventStore eventStore(DataSource dataSource, EntityManagerProvider entityManagerProvider) throws SQLException {
        JpaEventStore jpaEventStore = new JpaEventStore(entityManagerProvider);
        jpaEventStore.setDataSource(dataSource);
        return jpaEventStore;
    }
    @Bean
    public EventSourcingRepository<ShoppingCart> shoppingCartRepository(EventBus eventBus, EventStore eventStore) {
        EventSourcingRepository<ShoppingCart> repository = new EventSourcingRepository<>(ShoppingCart.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;
    }

    @Bean
    public AggregateAnnotationCommandHandler<ShoppingCart> shoppingCartCommandHandler(EventSourcingRepository<ShoppingCart> shoppingCartRepository,
                                                                        SpringBeanParameterResolverFactory springBeanParameterResolverFactory,
                                                                        CommandBus commandBus) {

        CommandTargetResolver commandTargetResolver =  new AnnotationCommandTargetResolver();
        AggregateAnnotationCommandHandler<ShoppingCart> commandHandler = new AggregateAnnotationCommandHandler<>(
                ShoppingCart.class,
                shoppingCartRepository,
                commandTargetResolver,
                springBeanParameterResolverFactory);

        for (String supportedCommand : commandHandler.supportedCommands()) {
            commandBus.subscribe(supportedCommand, commandHandler);
        }
        return commandHandler;
    }

//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource){
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setDataSource(dataSource);
//        Properties properties = new Properties();
//        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
//        schedulerFactoryBean.setQuartzProperties(properties);
//        return schedulerFactoryBean;
//    }
//
//    @Bean
//    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
//        return schedulerFactoryBean.getScheduler();
//    }
//
//    @Bean
//    public EventScheduler eventScheduler(SpringTransactionManager springTransactionManager, EventBus eventBus, Scheduler scheduler) throws SchedulerException {
//        QuartzEventScheduler eventScheduler = new QuartzEventScheduler();
//        eventScheduler.setEventBus(eventBus);
//        eventScheduler.setTransactionManager(springTransactionManager);
//        eventScheduler.setScheduler(scheduler);
//        eventScheduler.initialize();
//        return eventScheduler;
//    }
}
