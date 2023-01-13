package Domain.SaveLoad;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

public class MongoSaveLoad {
    MongoClient mongoClient;

    public MongoSaveLoad() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.

        MongoClient mongoClient = MongoClients.create("mongodb+srv://bambi:1234@cluster0.1yivv1e.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("sampleDB");
        MongoCollection<Document> collection = database.getCollection("sampleCollection");

        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 2052).append("y", 102));
        collection.insertOne(doc);

        mongoClient.close();
        hello();

    }

    public void hello() {
        System.out.println("server connection successfully done");
    }
}