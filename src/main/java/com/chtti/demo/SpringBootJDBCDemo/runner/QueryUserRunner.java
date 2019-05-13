package com.chtti.demo.SpringBootJDBCDemo.runner;

import com.chtti.demo.SpringBootJDBCDemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class QueryUserRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataManipulationRunner.class);
    private static final String QUERY_BY_ID = "SELECT id, username, password FROM users WHERE id = :id";

    @Autowired
    NamedParameterJdbcTemplate template;

    @Override
    public void run(String... args) throws Exception {
        SqlParameterSource source = new MapSqlParameterSource("id", 1);
        User user = template.queryForObject(QUERY_BY_ID, source, (rs, rowNum) -> {
            LOGGER.info(String.format("rowNum=%d", rowNum));
            return new User(rs.getString("username"), rs.getString("password"));
        });
        LOGGER.info(String.format("[QueryUserRunner]:%s", user));
    }

}
