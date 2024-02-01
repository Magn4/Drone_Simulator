package Formatter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API.Formatter.Drones.Drone;
import API.Formatter.Drones.DroneDynamics;
import API.Formatter.Drones.DroneType;

import java.util.ArrayList;
import java.util.List;

public class JsonFormatter2 {
    public static <T> List<T> ReadDroneList2(Class<T> droneClass, String jsonData) {
        List<T> droneList = new ArrayList<>();
    
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonData);
    
            // Handle single object case
            if (root.isObject()) {
                T drone = mapper.convertValue(root, droneClass);
                droneList.add(drone);
            } else if (root.isArray()) {
                JsonNode resultsNode = root.path("results");
                int number = 0;
                for (JsonNode node : resultsNode) {
                    number++;
                    T drone = mapper.convertValue(node, droneClass);
                    droneList.add(drone);
                }
            }
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace(); // Log the exception or handle it appropriately
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception or handle it appropriately
        }
    
        return droneList;
    }
    
    
    
}
