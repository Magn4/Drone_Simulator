package API.Formatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Formatter {
    public static void main(String[] args) {
        
    }
    // Create an ObjectMapper instance (Jackson library)
    private static ObjectMapper objectMapper = new ObjectMapper();

    // Method to format the JSON file
    public static void formatchange(int type) { /*String filePath, (need to be used after end of demo)*/ 
        

        // Specify the path to your JSON file
        String jsonFilePath = "/Users/taha/Desktop/Uni/Java_Project/Drone_Simulator/src/main/java/API/Responses/response.json";

        
        switch (type) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:  
            
            try {
                
                // Read the JSON file into a JsonNode
                JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));
    
                // Extract values from the JsonNode and assign them to variables
                int id = rootNode.get("id").asInt();
                String manufacturer = rootNode.get("manufacturer").asText();
                String typeName = rootNode.get("typename").asText();
                int weight = rootNode.get("weight").asInt();
                int maxSpeed = rootNode.get("max_speed").asInt();
                int batteryCapacity = rootNode.get("battery_capacity").asInt();
                int controlRange = rootNode.get("control_range").asInt();
                int maxCarriage = rootNode.get("max_carriage").asInt();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            break;
        
            default:
                System.out.println("Invalid option");
                break;
    }
    
    }
}
