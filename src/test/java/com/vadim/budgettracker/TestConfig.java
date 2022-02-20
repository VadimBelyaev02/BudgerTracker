package com.vadim.budgettracker;

import com.vadim.budgettracker.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.vadim.budgettracker")
@TestPropertySource("src/test/resources/properties/db.properties")
public class TestConfig extends ApplicationConfig {

    private final Environment environment;

    public TestConfig(Environment environment) {
        super(environment);
        this.environment = environment;
    }


//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(dataSource());
//        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
//        factory.setPackagesToScan(environment.getRequiredProperty("packagesToScan"));
//        Properties props = new Properties();
//        props.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//      //  props.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        props.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        factory.setJpaProperties(props);
//        return factory;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
//        return new HibernateJpaVendorAdapter();
//    }
//
//    @Bean("entityManager")
//    public EntityManager entityManager() {
//        return Objects.requireNonNull(entityManagerFactory().getObject()).createEntityManager();
//    }
}
