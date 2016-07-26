package aron.sinoai.consolemongodb.main;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.sql.*;

/**
 */
public class UserDumper {
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private PropertiesController propertiesController = new PropertiesController();

    public void dumpUsers() throws UnknownHostException {
        final MongoClientOptions.Builder builder = new MongoClientOptions.Builder()
                .connectionsPerHost(150)
                .cursorFinalizerEnabled(true);

        final String mongoUrl = propertiesController.getMongoUrl();
        final MongoClientURI mongoClientURI = new MongoClientURI(mongoUrl, builder);
        final MongoClient mongoClient = new MongoClient(mongoClientURI);
        final DB database = mongoClient.getDB(mongoClientURI.getDatabase());

        System.out.println("=======================");

        final DBCollection users = database.getCollection("users");

        final DBCursor dbObjects = users.find();

        while (dbObjects.hasNext()) {
            final DBObject item = dbObjects.next();

            final String userName = (String) item.get("userName");
            final String password = (String) item.get("password");

            System.out.printf("username : %s; password: %s; \n",  userName, password);
        }
        System.out.println("=======================");

    }

}
