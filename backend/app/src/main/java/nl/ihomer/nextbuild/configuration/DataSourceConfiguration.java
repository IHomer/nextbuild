package nl.ihomer.nextbuild.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 *
 * Configuration class containing the general configuration of the datasources.
 */
@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//    basePackages = {"nl.zep", "org.axonframework.eventstore.jpa", "org.axonframework.saga.repository.jpa"},
//    repositoryFactoryBeanClass = ZepRepositoryFactoryBean.class
//)
public class DataSourceConfiguration {

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       EntityManagerFactoryBuilder builder) {

        return builder
            .dataSource(dataSource)
            .packages("nl.ihomer.nextbuild", "org.axonframework.eventstore.jpa")
            .persistenceUnit("nextbuild")

            .properties(jpaProperties.getHibernateProperties(dataSource))

            .build();
    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.zep")
//    public DataSource zepDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource.boris")
//    public DataSource borisDataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
