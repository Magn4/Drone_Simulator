package API.Formatter.CreateDrone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API.Formatter.Drones.Drone;

public class droneNode {
    

     public static Drone DroneFromJsonNode(JsonNode node, int number, ObjectMapper mapper) {

        int id = node.path("id").asInt();
        String dronetype = node.path("dronetype").asText();
        String serialnumber = node.path("serialnumber").asText();
        int carriage_weight = node.path("carriage_weight").asInt();
        String carriage_type = node.path("carriage_type").asText();
        String created = node.path("created").asText();

        return new Drone(number, id, dronetype, serialnumber, carriage_weight, carriage_type, created);
    }


}
