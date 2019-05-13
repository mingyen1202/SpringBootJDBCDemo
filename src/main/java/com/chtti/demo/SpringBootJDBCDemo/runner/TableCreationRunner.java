package com.chtti.demo.SpringBootJDBCDemo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableCreationRunner implements CommandLineRunner {
    @Autowired
    JdbcTemplate template;

    private static final Logger LOGGER = LoggerFactory.getLogger(TableCreationRunner.class);

    private static final String SQL = "";

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("trying to create a table");
        template.execute("CREATE TABLE demo_table(id serial, username VARCHAR(255), PASSWORD VARCHAR(255));");
        LOGGER.info("after table creation");
    }
}
