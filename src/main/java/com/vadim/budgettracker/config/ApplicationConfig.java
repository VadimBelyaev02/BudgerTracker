package com.vadim.budgettracker.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "com.vadim.budgettracker")
@PropertySource("classpath:properties/db.properties")
@EnableJpaRepositories(basePackages = "com.vadim.budgettracker.dao")
public class ApplicationConfig {

    private final Environment environment;

    public ApplicationConfig(Environment environment) {
        this.environment = environment;
    }

   @Bean
     public BasicDataSource dataSource() {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("db.driverClassName"));
//        dataSource.setUsername(environment.getRequiredProperty("db.username"));
//        dataSource.setPassword(environment.getRequiredProperty("db.password"));
//        dataSource.setUrl(environment.getRequiredProperty("db.url"));
//        return dataSource;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        factory.setPackagesToScan(environment.getRequiredProperty("packagesToScan"));
        Properties props = new Properties();
        props.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        props.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        props.put("hibernate.ddl-auto", environment.getRequiredProperty("hibernate.ddl-auto"));
        factory.setJpaProperties(props);
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean("entityManager")
    public EntityManager entityManager() {
        return Objects.requireNonNull(entityManagerFactory().getObject()).createEntityManager();
    }
}
