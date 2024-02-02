package API.Formatter.CreateDrone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API.Formatter.Drones.DroneType;


/**
 *
 * @author Taha
 */

public class droneTypeJson {
    
      public static DroneType DroneTypeFromJsonNode(JsonNode node, int number, ObjectMapper mapper) {
        int id = node.path("id").asInt();
        String manufacturer = node.path("manufacturer").asText();
        String typename = node.path("typename").asText();
        int weight = node.path("weight").asInt();
        int max_speed = node.path("max_speed").asInt();
        int battery_capacity = node.path("battery_capacity").asInt();
        int control_range = node.path("control_range").asInt();
        int max_carriage = node.path("max_carriage").asInt();


        return new DroneType(number, id, manufacturer, typename, weight, max_speed, battery_capacity, control_range, max_carriage);

    }
    
}
