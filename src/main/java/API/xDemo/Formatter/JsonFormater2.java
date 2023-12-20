/*
 * Still need to study how I/O streams work
 * 1. Need to add a try catch case with a value x that starts from 0 and keeps getting up intill it gets
 * to the number of users to be able to give every user his values
 */
package API.xDemo.Formatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFormater2 {
    public static void main(String[] args) {

        // Specify the path to your JSON file
        String jsonFilePath = "/Users/taha/Desktop/Uni/Java_Project/Drone_Simulator/src/main/java/API/Data/Response.json";

        // Call the method to read and process the JSON file
        processJsonFile(jsonFilePath, 1);
    }

    public static void processJsonFile(String filePath, int InfoType) {
        try {

        ObjectMapper objectMapper = new ObjectMapper();

        // JsonNode rootNode = objectMapper.readTree(new File(filePath));
        
        switch (InfoType) {
            case 1:
               
            /* 
                // Extract values from the JsonNode and assign them to variables
                int id = rootNode.get("id").asInt();
                String manufacturer = rootNode.get("manufacturer").asText();
                String typeName = rootNode.get("typename").asText();
                int weight = rootNode.get("weight").asInt();
                int maxSpeed = rootNode.get("max_speed").asInt();
                int batteryCapacity = rootNode.get("battery_capacity").asInt();
                int controlRange = rootNode.get("control_range").asInt();
                int maxCarriage = rootNode.get("max_carriage").asInt();
                break;
        */
            default:
            System.out.println("Error");
                break;
        }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}