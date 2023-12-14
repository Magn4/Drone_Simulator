package API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileReader {
    public static void main(String[] args) {
        // Specify the path to your JSON file
        String jsonFilePath = "/Users/taha/Desktop/Uni/Java_Project/Drone_Simulator/src/main/java/API/Responses/response.json";

        // Call the method to read and process the JSON file
        processJsonFile(jsonFilePath);
    }

    public static void processJsonFile(String filePath) {
        try {
            // Create an ObjectMapper instance (Jackson library)
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            // Extract values from the JsonNode and assign them to variables
            int id = rootNode.get("id").asInt();
            String manufacturer = rootNode.get("manufacturer").asText();
            String typeName = rootNode.get("typename").asText();
            int weight = rootNode.get("weight").asInt();
            int maxSpeed = rootNode.get("max_speed").asInt();
            int batteryCapacity = rootNode.get("battery_capacity").asInt();
            int controlRange = rootNode.get("control_range").asInt();
            int maxCarriage = rootNode.get("max_carriage").asInt();

            // Display the extracted values
            System.out.println("ID: " + id);
            System.out.println("Manufacturer: " + manufacturer);
            System.out.println("TypeName: " + typeName);
            System.out.println("Weight: " + weight);
            System.out.println("Max Speed: " + maxSpeed);
            System.out.println("Battery Capacity: " + batteryCapacity);
            System.out.println("Control Range: " + controlRange);
            System.out.println("Max Carriage: " + maxCarriage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
