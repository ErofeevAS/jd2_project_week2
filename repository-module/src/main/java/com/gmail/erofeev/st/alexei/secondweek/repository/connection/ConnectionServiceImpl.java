package com.gmail.erofeev.st.alexei.secondweek.repository.connection;

import com.gmail.erofeev.st.alexei.secondweek.repository.exception.DataBaseException;
import com.gmail.erofeev.st.alexei.secondweek.repository.properties.DataBaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


@Component
public class ConnectionServiceImpl implements ConnectionService {
    private static final Logger logger = LogManager.getLogger(ConnectionServiceImpl.class);
    private final DataBaseProperties dataBaseProperties;

    @Autowired
    public ConnectionServiceImpl(DataBaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.getDriver());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        this.dataBaseProperties = databaseProperties;
    }

    @PostConstruct
    private void createDataBaseTable() {
        logger.debug("initialization tables in the database... ");
        String createDocumentTableQuery = "CREATE TABLE IF NOT EXISTS documents( id BIGINT auto_increment PRIMARY KEY,  unique_number VARCHAR(255) NOT NULL UNIQUE, description VARCHAR(255), deleted BOOLEAN DEFAULT FALSE NOT NULL)";
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.execute(createDocumentTableQuery);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataBaseException(e.getMessage(), e);
        }
        logger.debug("initialization tables in the database was successful ");
    }

    @Override
    public Connection getConnection() {
        logger.debug("getting a connection to a database...");
        Properties properties = new Properties();
        String dataBaseUrl = dataBaseProperties.getUrl();
        properties.setProperty("user", dataBaseProperties.getUsername());
        properties.setProperty("password", dataBaseProperties.getPassword());
        properties.setProperty("serverTimezone", dataBaseProperties.getServerTimezone());
        Connection connection;
        try {
            connection = DriverManager.getConnection(dataBaseUrl, properties);
            logger.debug("connection was established");
            return connection;
        } catch (SQLException e) {
            String message = "Can't connect to dataBase " + e.getMessage();
            logger.error(message);
            throw new DataBaseException(message, e);
        }
    }
}