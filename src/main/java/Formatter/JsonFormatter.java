package Formatter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Formatter.Drones.DroneType;
import Formatter.Drones.Drone;
import Formatter.Drones.DroneDynamics;

import java.util.ArrayList;
import java.util.List;

public class JsonFormatter {
    public static <T> List<T> ReadDroneList(int type, String jsonData) {

        List<T> droneList = new ArrayList<>();

        try {
            System.out.println("\n\n");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonData);
            JsonNode resultsNode = root.path("results");

            switch (type) {
                case 1:
                    if (resultsNode.isArray()) {
                        int number = 0;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone;
                            int id = node.path("id").asInt();
                            String manufacturer = node.path("manufacturer").asText();
                            String typename = node.path("typename").asText();
                            int weight = node.path("weight").asInt();
                            int max_speed = node.path("max_speed").asInt();
                            int battery_capacity = node.path("battery_capacity").asInt();
                            int control_range = node.path("control_range").asInt();
                            int max_carriage = node.path("max_carriage").asInt();

                            drone = (T) new DroneType(number, id, manufacturer, typename, weight, max_speed, battery_capacity, control_range, max_carriage);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 2:
                    if (resultsNode.isObject()) {
                        // Handle single object case
                        int number = 1;
                        int id = resultsNode.path("id").asInt();
                        String dronetype = resultsNode.path("dronetype").asText();
                        String serialnumber = resultsNode.path("serialnumber").asText();
                        int carriage_weight = resultsNode.path("carriage_weight").asInt();
                        String carriage_type = resultsNode.path("carriage_type").asText();
                        String created = resultsNode.path("created").asText();

                        T drone = (T) new Drone(number, id, dronetype, serialnumber, carriage_weight, carriage_type, created);
                        droneList.add(drone);
                    } else if (resultsNode.isArray()) {
                        int number = 1;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone;
                            int id = node.path("id").asInt();
                            String dronetype = node.path("dronetype").asText();
                            String serialnumber = node.path("serialnumber").asText();
                            int carriage_weight = node.path("carriage_weight").asInt();
                            String carriage_type = node.path("carriage_type").asText();
                            String created = node.path("created").asText();


                            drone = (T) new Drone(number, id, dronetype, serialnumber, carriage_weight, carriage_type, created);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 3:
                    if (resultsNode.isArray()) {
                        int number = 1;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone;
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

                            drone = (T) new DroneDynamics(number, droneURL, timestamp, speed, align_roll, align_pitch, align_yaw, longitude, latitude, battery_status, last_seen, status);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 4:
                    // This is for a one-case scenario.
                    
                    JsonNode jsonNode = mapper.readTree(jsonData);
                    Drone droneData = mapper.treeToValue(jsonNode, Drone.class);

                    T drone;
                    int number = 1;
                    int id = droneData.getId();
                    String dronetype = droneData.getDronetype();
                    String serialnumber = droneData.getSerialnumber();
                    int carriage_weight = droneData.getCarriage_weight();
                    String carriage_type = droneData.getCarriage_type();
                    String created = droneData.getCreated();


                    drone = (T) new Drone(number, id, dronetype, serialnumber, carriage_weight, carriage_type, created);
                    droneList.add(drone);
                    break;

                case 5:
    
                    JsonNode jsonNode2 = mapper.readTree(jsonData);
                    DroneType droneTypeData = mapper.treeToValue(jsonNode2, DroneType.class);

                    // T drone2;
                    int number2 = 1;
                    int id2 = droneTypeData.getId();
                    String manufacturer = droneTypeData.getManufacturer();
                    String typename = droneTypeData.getTypename();
                    int weight = droneTypeData.getWeight();
                    int max_speed = droneTypeData.getMax_speed();
                    int battery_capacity = droneTypeData.getBattery_capacity();
                    int control_range = droneTypeData.getControl_range();
                    int max_carriage = droneTypeData.getMax_carriage();


                    drone = (T) new DroneType(number2, id2, manufacturer, typename, weight, max_speed, battery_capacity, control_range, max_carriage);
                    droneList.add(drone);



                    break;
                default:
                    break;
            }
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        return droneList;
    }
}
