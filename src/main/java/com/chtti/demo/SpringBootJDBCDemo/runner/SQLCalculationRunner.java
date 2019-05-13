package com.chtti.demo.SpringBootJDBCDemo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class SQLCalculationRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(SQLCalculationRunner.class);

    @Autowired
    NamedParameterJdbcTemplate template;

    @Override
    public void run(String... args) throws Exception {
        String sql = "SELECT 888";
        SqlParameterSource parameter = new MapSqlParameterSource();
        Integer result = template.queryForObject(sql, parameter, Integer.class);
        LOGGER.info("result=" + result);
    }
}
