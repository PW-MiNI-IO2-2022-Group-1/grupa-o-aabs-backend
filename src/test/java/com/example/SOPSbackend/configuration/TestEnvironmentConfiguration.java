package com.example.SOPSbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Profile("test")
@Configuration
public class TestEnvironmentConfiguration {
    @Bean
    @Primary
    public DataSource dataSource() {
       return new EmbeddedDatabaseBuilder()
               .generateUniqueName(true)
               .setType(EmbeddedDatabaseType.H2)
               .setScriptEncoding("UTF-8")
               .ignoreFailedDrops(true)
               .build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application-test.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}
