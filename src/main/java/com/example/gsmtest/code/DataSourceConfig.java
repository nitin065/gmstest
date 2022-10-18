package com.example.gsmtest.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataSourceConfig {

    @Value("${application.secret}")
    private String csql;

    @Bean(name = "masterDataSource")
    public DataSource dataSource(DataSourceProperties properties) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CSQL csqlData = objectMapper.readValue(csql, CSQL.class);
        return properties.initializeDataSourceBuilder()
                .username(csqlData.username)
                .password(csqlData.password)
                .build();
    }
}
