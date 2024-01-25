package Formatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Formatter.Drones.Drone;
import Formatter.Drones.DroneType;
import Formatter.Drones.DroneDynamics;

import java.util.ArrayList;
import java.util.List;

public class JsonFormatter {
    public static <T> List<T> ReadDroneList(int type, String jsonData) {
        List<T> droneList = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonData);
            JsonNode resultsNode = root.path("results");

            switch (type) {
                case 1:
                    if (resultsNode.isArray()) {
                        int number = 0;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone = (T) createDroneTypeFromJsonNode(node, number, mapper);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 2:
                    if (resultsNode.isArray()) {
                        int number = 0;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone = (T) createDroneFromJsonNode(node, number, mapper);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 3:
                    if (resultsNode.isArray()) {
                        int number = 0;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone = (T) createDroneDynamicsFromJsonNode(node, number, mapper);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 4:
                    T drone = (T) createDroneFromJsonData(jsonData, mapper);
                    droneList.add(drone);
                    break;

                case 5:
                    T droneType = (T) createDroneTypeFromJsonData(jsonData, mapper);
                    droneList.add(droneType);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return droneList;
    }

    private static Drone createDroneFromJsonNode(JsonNode node, int number, ObjectMapper mapper) {
        int id = node.path("id").asInt();
        String dronetype = node.path("dronetype").asText();
        String serialnumber = node.path("serialnumber").asText();
        int carriage_weight = node.path("carriage_weight").asInt();
        String carriage_type = node.path("carriage_type").asText();
        String created = node.path("created").asText();

        return new Drone(number, id, dronetype, serialnumber, carriage_weight, carriage_type, created);
    }

    private static DroneDynamics createDroneDynamicsFromJsonNode(JsonNode node, int number, ObjectMapper mapper) {
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

    private static Drone createDroneFromJsonData(String jsonData, ObjectMapper mapper) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonData);
            return mapper.treeToValue(jsonNode, Drone.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static DroneType createDroneTypeFromJsonData(String jsonData, ObjectMapper mapper) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonData);
            return mapper.treeToValue(jsonNode, DroneType.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static DroneType createDroneTypeFromJsonNode(JsonNode node, int number, ObjectMapper mapper) {
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
