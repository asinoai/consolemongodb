package aron.sinoai.consolemongodb.main;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 */
public class UserCreator {
    private static final String[] DEFAULT_USERS = {"Jo", "Joe", "Ursula", "Urs"};
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private PropertiesController propertiesController = new PropertiesController();

    public void createDefaultUsers() throws UnknownHostException {

        final MongoClientOptions.Builder builder = new MongoClientOptions.Builder()
                .connectionsPerHost(150)
                .cursorFinalizerEnabled(true);

        final String mongoUrl = propertiesController.getMongoUrl();
        final MongoClientURI mongoClientURI = new MongoClientURI(mongoUrl, builder);
        final MongoClient mongoClient = new MongoClient(mongoClientURI);
        final DB database = mongoClient.getDB(mongoClientURI.getDatabase());

        final DBCollection users = database.getCollection("users");

        for (final String userName : DEFAULT_USERS) {
            final DBObject dbObject = new BasicDBObject();
            dbObject.put("userName", userName);
            dbObject.put("password", "welcome" + userName);

            users.insert(dbObject);
        }

        LOGGER.info("Inserted " + DEFAULT_USERS.length +" user records!");
    }

}
