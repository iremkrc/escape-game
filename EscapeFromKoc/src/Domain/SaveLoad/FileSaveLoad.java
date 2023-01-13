package Domain.SaveLoad;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Domain.Controllers.GameController;
import Domain.Controllers.LoginController;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import javax.sound.midi.SysexMessage;

public class FileSaveLoad {
    private GameController game = GameController.getInstance();
    private LoginController login = new LoginController(game);

    public void write(SaveObject currSave) {
        JsonObject save = currSave.generateSaveJson();

        String playerName = login.getLoginName();
        String name = "save_" + playerName;

        File myFile =  new File("EscapeFromKoc/src/Domain/SaveLoad/Saves/"+name);
        File parent = myFile.getParentFile();

        if(!parent.exists()) {
            parent.mkdirs();
        }
        try (FileWriter file = new FileWriter("EscapeFromKoc/src/Domain/SaveLoad/Saves/"+ name +".json")) {

            file.write(save.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved to file");
    }

    public JsonObject read() throws JsonSyntaxException, JsonIOException, FileNotFoundException {

        String playerName = login.getLoginName();
        String name = "save_" + playerName;

        Object obj = JsonParser.parseReader(new FileReader("EscapeFromKoc/src/Domain/SaveLoad/Saves/" + name + ".json"));
        JsonObject jo = (JsonObject) obj; //all json imported

        System.out.println("*************** GAME DATA IS *************\n\n");
        System.out.println(jo);
        System.out.println("\n\n*************** GAME DATA IS *************");

        return jo;

    }



}