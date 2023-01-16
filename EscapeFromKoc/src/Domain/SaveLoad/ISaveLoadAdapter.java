package Domain.SaveLoad;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;

public interface ISaveLoadAdapter {
    void save();
    void load() throws JsonSyntaxException, JsonIOException, FileNotFoundException;
}