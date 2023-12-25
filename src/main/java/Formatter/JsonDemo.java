package Formatter;

import java.io.File;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Formatter.Drones.DroneType1;
import Formatter.Drones.DroneType2;

import java.util.ArrayList;
import java.util.List;

public class JsonDemo {
    public static List<DroneType1> ReadDroneList(String JsonFileName, int Type) {
        List<DroneType1> droneList = null;
        try {
            System.out.println("\n\n");
            ObjectMapper mapper = new ObjectMapper();

            // String jsonFileName = "Test.json";
            String jsonFilePath = "src/main/java/Data/" + JsonFileName;

            JsonNode root = mapper.readTree(new File(jsonFilePath));

            JsonNode ResultsNode = root.path("results");
            switch (Type) {
                case 1:
                if (ResultsNode.isArray()) {
                // System.out.println("Is this node an Array? " + ResultsNode.isArray());

                // Create a list to store Drone objects
                droneList = new ArrayList<>();

                int number = 0;
                for (JsonNode node : ResultsNode) {
                    number++;
                    int id = node.path("id").asInt();
                    String manufacturer = node.path("manufacturer").asText();
                    String typename = node.path("typename").asText();
                    int weight = node.path("weight").asInt();
                    int max_speed = node.path("max_speed").asInt();
                    int battery_capacity = node.path("battery_capacity").asInt();
                    int control_range = node.path("control_range").asInt();
                    int max_carriage = node.path("max_carriage").asInt();

                    DroneType1 drone = new DroneType1(number, id, manufacturer, typename, weight, max_speed, battery_capacity, control_range, max_carriage);
                    droneList.add(drone);
                }
                System.out.println("\n\n");
                System.out.println(droneList.get(3).getId());
                System.out.println("\n\n");


             
            }
                    
                    break; 
                case 2:
                if (ResultsNode.isArray()) {
                    droneList = new ArrayList<>();
                    int number = 0;
                    for (JsonNode node : ResultsNode) {
                        number++;
                        int id = node.path("id").asInt();
                        String dronetype = node.path("dronetype").asText();
                        String serialnumber = node.path("serialnumber").asText();
                        int carriage_weight = node.path("carriage_weight").asInt();
                        String carriage_type = node.path("carriage_type").asText();

                        DroneType2 drone = new DroneType2(number, id, dronetype, serialnumber, carriage_weight, carriage_type);
                        droneList.add(drone);
                    }
                    
                }

                    
                    break;
                case 3:
                    
                    break;

                case 4:
                    
                    break;
                    
                default:
                    break;
            }
            
            
        } catch (JsonGenerationException e) {

            e.printStackTrace();
            return new ArrayList<>();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        return droneList;
    }
}







       /*for (Drones drone : droneList) {
                    System.out.println("\n\n");
                    System.out.println("Drone " + drone.getNumber() + ": " +
                            "ID = " + drone.getId() + 
                            ", Manufacturer = " + drone.getManufacturer() + 
                            ", Type Name = " + drone.getTypename() + 
                            ", Weight = " + drone.getWeight() + 
                            ", Max Speed = " + drone.getMax_speed() + 
                            ", Battery Capacity = " + drone.getBattery_capacity() + 
                            ", Control Range = " + drone.getControl_range() + 
                            ", Max Carriage = " + drone.getMax_carriage());
                }*/
