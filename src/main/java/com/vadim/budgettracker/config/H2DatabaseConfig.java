package com.vadim.budgettracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:properties/db.properties")
public class H2DatabaseConfig {

    private final Environment environment;

    public H2DatabaseConfig(Environment environment) {
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

//    @Bean(name = "h2TestDataSource")
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(false)
//                .setName("testdb")
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:sql/create-db.sql")
//                .addScript("classpath:sql/create-users.sql")
//                .addScript("classpath:sql/create-categories.sql")
//                .addScript("classpath:sql/create-confirmations.sql")
//                .addScript("classpath:sql/create-operations.sql")
//                .setScriptEncoding("UTF-8")
//                .ignoreFailedDrops(true)
//                .build();
//    }



}
