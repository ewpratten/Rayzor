package ca.retrylife.ics4u.rayzor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class SceneLoader {

    public static void loadFromJSON(String filepath, Scene scene) throws FileNotFoundException, ParseException, IOException {
        
        // Load the JSON file
        JSONObject json = (JSONObject) new JSONParser().parse(new FileReader(filepath));

        System.out.println(json);
    }
}