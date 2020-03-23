package generator.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder .url("jdbc:h2:mem:test")
                .username("sa")
                .password("sa")
                .driverClassName("org.h2.Driver");
        return builder.build();
    }
}
