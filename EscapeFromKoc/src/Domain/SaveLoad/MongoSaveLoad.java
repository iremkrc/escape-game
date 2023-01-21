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
    private MongoCollection<Document> collection;
    MongoDatabase database;
    
    public MongoSaveLoad() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.

        this.mongoClient = MongoClients.create("mongodb+srv://bambi:1234@cluster0.1yivv1e.mongodb.net/?retryWrites=true&w=majority");
        this.database = mongoClient.getDatabase("bambiDB");
        this.collection = database.getCollection("bambiCol");
        
        /*	
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 2052).append("y", 102));
        collection.insertOne(doc);
         */
        hello();
    }

    public void hello() {
        System.out.println("server connection successfully done");
    }
    
    public void insert(Document obj) {
    	System.out.println("-------------------------------------------------------" + obj + "--------------------------------------------------------------------");
    	String username = (String) obj.get("playerName");

    	Document query = new Document("playerName", username);
		long count = this.collection.count(query);
		if (count > 0) {
			Document toUpdate = new Document();
			toUpdate.append("$set", obj);
			this.collection.replaceOne(query, obj);
			this.collection.deleteOne(query);
		} else {
			this.collection.insertOne(obj);			
		}
		System.out.println("Saved to mongodb");
	}
   
    public void deleteGameData(Document obj) {
    	String username = (String) obj.get("playerName");

    	Document query = new Document("playerName", username);
		long count = this.collection.count(query);
		if (count > 0) {
			
			this.collection.deleteOne(query);
		}
		
    }
	
	public Document read(String username) {
		Document query = new Document("playerName", username);
		Document loadDocument = this.collection.find(query).first();
		return loadDocument;
	}
    
}
