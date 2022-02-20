package com.vadim.budgettracker;

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
@TestPropertySource("classpath:properties/db.properties")
public class TestConfig {

    private final Environment environment;

    public TestConfig(Environment environment) {
        this.environment = environment;
    }

//    @Bean(name = "h2DataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("db.h2.DriverClassName"));
//        dataSource.setUsername(environment.getRequiredProperty("db.h2.username"));
//        dataSource.setPassword(environment.getRequiredProperty("db.h2.password"));
//        dataSource.setUrl(environment.getRequiredProperty("db.h2.url"));
//        return dataSource;
//    }

//    @Bean
//    public DataSource dataSource(
//            @Value("${datasource.dbname}") String dbname,
//            @Value("${datasource.script}") String script) {
//
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .setName(dbname)
//                .addScript(script)
//                .build();
//    }

    @Bean(name = "h2TestDataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(false)
                .setName("testdb")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:sql/create-db.sql")
                .addScript("classpath:sql/create-users.sql")
                //      .addScript("classpath:sql/create-categories.sql")
                //      .addScript("classpath:sql/create-confirmations.sql")
                //      .addScript("classpath:sql/create-operations.sql")
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }
    /*
    jdbc.driverClassName=org.h2.Driver
jdbc.url=jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1

hibernate.dialect=org.hibernate.dialect.H2Dialect
hibernate.hbm2ddl.auto=create
     */


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
