package API.Formatter.CreateDrone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API.Formatter.Drones.DroneType;

public class droneTypeNode {
 
    public static DroneType DroneTypeFromJsonData(String jsonData, ObjectMapper mapper) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonData);
            return mapper.treeToValue(jsonNode, DroneType.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
