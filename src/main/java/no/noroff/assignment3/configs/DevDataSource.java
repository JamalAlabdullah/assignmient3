package no.noroff.assignment3.configs;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevDataSource {
    @Bean
    public DataSource getDevDataSource(){
        DataSourceBuilder builder= DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/Assignment3");
        builder.username("postgres");
        builder.password("454107");
        return builder.build();
    }

}
