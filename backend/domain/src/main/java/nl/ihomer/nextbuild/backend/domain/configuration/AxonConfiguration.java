package nl.ihomer.nextbuild.backend.domain.configuration;

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
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.quartz.QuartzEventScheduler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.jpa.JpaEventStore;
import org.axonframework.saga.GenericSagaFactory;
import org.axonframework.saga.SagaFactory;
import org.axonframework.saga.SagaManager;
import org.axonframework.saga.SagaRepository;
import org.axonframework.saga.annotation.AnnotatedSagaManager;
import org.axonframework.saga.repository.jpa.JpaSagaRepository;
import org.axonframework.saga.spring.SpringResourceInjector;
import org.axonframework.serializer.xml.CompactDriver;
import org.axonframework.serializer.xml.XStreamSerializer;
import org.axonframework.unitofwork.SpringTransactionManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

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
        Map<String, Cluster> mapping = Collections.singletonMap("nl.zep.sharesadministration.view.handlers.view", replayingCluster);
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

//    @Bean
//    public JpaEventStore eventStore(DataSource zepDataSource, EntityManagerProvider entityManagerProvider) throws SQLException {
//        XStream xStream = new XStream(new CompactDriver());
//        xStream.setMode(XStream.NO_REFERENCES);
//        xStream.registerConverter(new DurationConverter());
//        xStream.registerConverter(new LocalDateConverter());
//        xStream.registerConverter(new LocalDateTimeConverter());
//        xStream.registerConverter(new PeriodConverter());
//        XStreamSerializer serializer = new XStreamSerializer(xStream);
//        JpaEventStore jpaEventStore = new JpaEventStore(entityManagerProvider, serializer);
//        jpaEventStore.setDataSource(zepDataSource);
//        return jpaEventStore;
//    }
//
//    @Bean
//    public SpringResourceInjector springResourceInjector(){
//        return new SpringResourceInjector();
//    }
//
//    @Bean
//    public SpringBeanParameterResolverFactory springBeanParameterResolverFactory(){
//        return new SpringBeanParameterResolverFactory();
//    }
//
//    @Bean
//    public EventSourcingRepository<Share> shareRepository(EventBus eventBus, EventStore eventStore) {
//        EventSourcingRepository<Share> repository = new EventSourcingRepository<>(Share.class, eventStore);
//        repository.setEventBus(eventBus);
//        return repository;
//    }
//
//    @Bean
//    public AggregateAnnotationCommandHandler<Share> shareCommandHandler(EventSourcingRepository<Share> shareRepository,
//                                                                        SpringBeanParameterResolverFactory springBeanParameterResolverFactory,
//                                                                        CommandBus commandBus) {
//
//        CommandTargetResolver commandTargetResolver =  new AnnotationCommandTargetResolver();
//        AggregateAnnotationCommandHandler<Share> commandHandler = new AggregateAnnotationCommandHandler<>(
//            Share.class,
//            shareRepository,
//            commandTargetResolver,
//            springBeanParameterResolverFactory);
//
//        for (String supportedCommand : commandHandler.supportedCommands()) {
//            commandBus.subscribe(supportedCommand, commandHandler);
//        }
//        return commandHandler;
//    }
//
//    @Bean
//    public EventSourcingRepository<Person> personRepository(EventBus eventBus, EventStore eventStore) {
//        EventSourcingRepository<Person> repository = new EventSourcingRepository<>(Person.class, eventStore);
//        repository.setEventBus(eventBus);
//        return repository;
//    }
//
//    @Bean
//    public AggregateAnnotationCommandHandler<Person> personCommandHandler(EventSourcingRepository<Person> personRepository,
//                                                                        SpringBeanParameterResolverFactory springBeanParameterResolverFactory,
//                                                                        CommandBus commandBus) {
//
//        CommandTargetResolver commandTargetResolver =  new AnnotationCommandTargetResolver();
//        AggregateAnnotationCommandHandler<Person> commandHandler = new AggregateAnnotationCommandHandler<>(
//            Person.class,
//            personRepository,
//            commandTargetResolver,
//            springBeanParameterResolverFactory);
//
//        for (String supportedCommand : commandHandler.supportedCommands()) {
//            commandBus.subscribe(supportedCommand, commandHandler);
//        }
//        return commandHandler;
//    }
//
//    @Bean
//    public EventSourcingRepository<SalesProcess> salesProcessRepository(EventBus eventBus, EventStore eventStore) {
//        EventSourcingRepository<SalesProcess> repository = new EventSourcingRepository<>(SalesProcess.class, eventStore);
//        repository.setEventBus(eventBus);
//        return repository;
//    }
//
//    @Bean
//    public AggregateAnnotationCommandHandler<SalesProcess> salesProcessCommandHandler(EventSourcingRepository<SalesProcess> salesProcessRepository,
//                                                                        SpringBeanParameterResolverFactory springBeanParameterResolverFactory,
//                                                                        CommandBus commandBus) {
//
//        CommandTargetResolver commandTargetResolver =  new AnnotationCommandTargetResolver();
//        AggregateAnnotationCommandHandler<SalesProcess> commandHandler = new AggregateAnnotationCommandHandler<>(
//            SalesProcess.class,
//            salesProcessRepository,
//            commandTargetResolver,
//            springBeanParameterResolverFactory);
//
//        for (String supportedCommand : commandHandler.supportedCommands()) {
//            commandBus.subscribe(supportedCommand, commandHandler);
//        }
//        return commandHandler;
//    }
//
//    @Bean
//    public SagaRepository sagaRepository(EntityManagerProvider entityManagerProvider, SpringResourceInjector springResourceInjector){
//        JpaSagaRepository repository = new JpaSagaRepository(entityManagerProvider);
//        repository.setResourceInjector(springResourceInjector);
//        return repository;
//    }
//
//    @Bean
//    public SagaFactory sagaFactory(SpringResourceInjector springResourceInjector){
//        GenericSagaFactory factory = new GenericSagaFactory();
//        factory.setResourceInjector(springResourceInjector);
//        return factory;
//    }
//
//    @Bean
//    public SagaManager sagaManager(SagaRepository sagaRepository, SagaFactory sagaFactory, EventBus eventBus) {
//        AnnotatedSagaManager manager = new AnnotatedSagaManager(sagaRepository, sagaFactory, RegisterSalesProcessSaga.class, OpenAndCloseSalesProcessSaga.class, CompleteSalesProcessSaga.class);
//        eventBus.subscribe(manager);
//        return manager;
//    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource zepDataSource){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(zepDataSource);
        Properties properties = new Properties();
        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        schedulerFactoryBean.setQuartzProperties(properties);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
        return schedulerFactoryBean.getScheduler();
    }

    @Bean
    public EventScheduler eventScheduler(SpringTransactionManager springTransactionManager, EventBus eventBus, Scheduler scheduler) throws SchedulerException {
        QuartzEventScheduler eventScheduler = new QuartzEventScheduler();
        eventScheduler.setEventBus(eventBus);
        eventScheduler.setTransactionManager(springTransactionManager);
        eventScheduler.setScheduler(scheduler);
        eventScheduler.initialize();
        return eventScheduler;
    }
}
