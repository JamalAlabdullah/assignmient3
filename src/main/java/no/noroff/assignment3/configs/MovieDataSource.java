package no.noroff.assignment3.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("movie")
public class MovieDataSource {
    @Bean
    public DataSource getDevDataSource(){
        DataSourceBuilder builder= DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://ec2-54-77-40-202.eu-west-1.compute.amazonaws.com:5432/da7c2200qa35lj");
        builder.username("hqaljcuedhxauk");
        builder.password("ba32c678f150d86bad0997cae22b55c4d9a5fad85cdd69e5172b6e6b8923c5a7");
        return builder.build();
    }
}
