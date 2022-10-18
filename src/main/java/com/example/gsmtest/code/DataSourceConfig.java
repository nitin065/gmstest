package com.example.gsmtest.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceConfig {

    @Value("${application.secret}")
    private String csql;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties getDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "masterDataSource")
    public DataSource dataSource() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        CSQL csqlData = objectMapper.readValue(csql, CSQL.class);

        DataSource dataSource =  getDatasourceProperties().initializeDataSourceBuilder()
                .username(csqlData.username)
                .password(csqlData.password)
                .build();
        return dataSource;
    }
}
