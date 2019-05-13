package com.chtti.demo.SpringBootJDBCDemo.runner;

import com.chtti.demo.SpringBootJDBCDemo.model.User;
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
    private static final String QUERY_DML = "SELECT id, username, password FROM demo_table WHERE username = ?";
    private static final String QUERY_ALL = "SELECT * FROM demo_table";

    @Autowired
    JdbcTemplate template;

    @Override
    public void run(String... args) throws Exception {
        createDemoTable();
        insertSampleData();
        queryAllData();
        querySampleData();
    }

    private void insertSampleData() {
        List<Object[]> names = Arrays.asList("Mark PWD1", "Peter PWD2", "John PWD3", "T PWD4").stream().map(s -> s.split(" ")).collect(Collectors.toList());
        LOGGER.info(String.format("names=%s", names));
        names.forEach(objects -> LOGGER.info(String.format("Insert into DB [%s, %s]", objects[0], objects[1])));
        template.batchUpdate(INSERT_DML, names);
    }

    private void createDemoTable() {
        LOGGER.info("trying to create a table");
        template.execute("CREATE TABLE demo_table(id serial, username VARCHAR(255), password VARCHAR(255));");
        LOGGER.info("after table creation");
    }

    private void queryAllData() {
        LOGGER.info("Query All Data");
        template.query(QUERY_ALL, (resultSet, rowNumber) ->
                new User(resultSet.getString("username"), resultSet.getString("password"))
        ).forEach(user -> LOGGER.info(user.toString()));
    }

    private void querySampleData() {
        LOGGER.info("Query Sample Data");
        template.query(QUERY_DML, new Object[]{"Mark"}, (resultSet, rowNumber) ->
            new User(resultSet.getString("username"), resultSet.getString("password"))
        ).forEach(user -> LOGGER.info(user.toString()));
    }
}
