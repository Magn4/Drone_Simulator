package src.Main.API.Demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Formatter {
    public static String beautifyJson(String json) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Object jsonObject = gson.fromJson(json, Object.class);
            return gson.toJson(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return json; // Return original JSON if formatting fails
        }
    }
    
}