package API.Formatter.CreateDrone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import API.Formatter.Drones.DroneDynamics;

public class droneDynamicsJson{


    public static DroneDynamics DroneDynamicsFromJsonNode(JsonNode node, int number, ObjectMapper mapper) {
       
        String droneURL = node.path("drone").asText();
        String timestamp = node.path("timestamp").asText();
        int speed = node.path("speed").asInt();
        String align_roll = node.path("align_roll").asText();
        String align_pitch = node.path("align_pitch").asText();
        String align_yaw = node.path("align_yaw").asText();
        String longitude = node.path("longitude").asText();
        String latitude = node.path("latitude").asText();
        int battery_status = node.path("battery_status").asInt();
        String last_seen = node.path("last_seen").asText();
        String status = node.path("status").asText();


        return new DroneDynamics(number, droneURL, timestamp, speed, align_roll, align_pitch, align_yaw, longitude, latitude, battery_status, last_seen, status);
    }
}