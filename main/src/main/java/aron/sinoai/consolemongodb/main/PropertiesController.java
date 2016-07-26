package aron.sinoai.consolemongodb.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */

public class PropertiesController {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesController.class);

    final Properties properties = new Properties();

    public PropertiesController() {
        initProperties();
    }

    private void initProperties() {
        final ClassLoader classLoader = PropertiesController.class.getClassLoader();

        try (final InputStream resourceAsStream = classLoader.getResourceAsStream("app.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public String getMongoUrl() {
        return properties.getProperty("mongo.url");
    }



}
