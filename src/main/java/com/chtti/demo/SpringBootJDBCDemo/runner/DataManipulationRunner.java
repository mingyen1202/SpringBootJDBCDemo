package com.chtti.demo.SpringBootJDBCDemo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataManipulationRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataManipulationRunner.class);
    private static final String INSERT_DML = "INSERT INTO demo_table(username, password) VALUES (?,?)";

    @Autowired
    JdbcTemplate template;

    @Override
    public void run(String... args) throws Exception {
        template.execute("CREATE TABLE demo_table(id serial, username VARCHAR(255), password VARCHAR(255));");

        List<Object[]> names = Arrays.asList("Mark PWD1", "Peter PWD2", "John PWD3", "T PWD4").stream().map(new Function<String, String[]>() {
            @Override
            public java.lang.String[] apply(java.lang.String s) {
                return s.split(" ");
            }
        }).collect(Collectors.toList());
        LOGGER.info(String.format("names=%s", names));
        names.forEach(new Consumer<Object[]>() {
            @Override
            public void accept(Object[] objects) {
                LOGGER.info(String.format("Insert into DB [%s, %s]", objects[0], objects[1]));
            }
        });
        template.batchUpdate(INSERT_DML, names);
    }
}
