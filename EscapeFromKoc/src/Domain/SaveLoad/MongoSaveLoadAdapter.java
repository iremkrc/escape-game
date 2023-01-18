package Domain.SaveLoad;

import java.io.FileNotFoundException;

import org.bson.Document;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class MongoSaveLoadAdapter implements ISaveLoadAdapter {
	private MongoSaveLoad mongoSaveLoad;
	private SaveObject currSave;
	
	public MongoSaveLoadAdapter() {
		this.mongoSaveLoad = new MongoSaveLoad();
		this.currSave = new SaveObject();
	}
	
	@Override
	public void save() {
		this.mongoSaveLoad.insert(this.currSave.toDBObject());
	}


	@Override
	public void load() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Document loadDoc = this.mongoSaveLoad.read((String) currSave.toDBObject().get("username"));
		if (loadDoc.equals(null)) {
			// No document was found with given username
			return;
		}
		
	}

}
