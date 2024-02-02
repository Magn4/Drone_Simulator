package API.Formatter.CreateDrone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API.Formatter.Drones.Drone;


/**
 *
 * @author Taha
 */

public class droneJson {
    
     public static Drone DroneFromJsonData(String jsonData, ObjectMapper mapper) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonData);
            return mapper.treeToValue(jsonNode, Drone.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
