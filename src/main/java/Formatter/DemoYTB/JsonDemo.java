package Formatter.DemoYTB;

import java.io.File;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonDemo {
    public static void main(String[] args) {
        try {
            System.out.println("\n\n");
            ObjectMapper mapper = new ObjectMapper();

            String jsonFileName = "Test.json";
            String jsonFilePath = "src/main/java/Data/" + jsonFileName;

            JsonNode root = mapper.readTree(new File(jsonFilePath));

            JsonNode ResultsNode = root.path("results");
            if (ResultsNode.isArray()) {
                System.out.println("Is this node an Array? " + ResultsNode.isArray());

                // Create a list to store Drone objects
                List<Drones> droneList = new ArrayList<>();

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

                    Drones drone = new Drones(number, id, manufacturer, typename, weight, max_speed, battery_capacity, control_range, max_carriage);
                    droneList.add(drone);
                }
                System.out.println("\n\n");
                System.out.println(droneList.get(3).getId());
                System.out.println("\n\n");


                for (Drones drone : droneList) {
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
                }
            }
        } catch (JsonGenerationException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

/* 
class Drone {
    private int number;
    private int id;
    private String typename;

    public Drone(int number, int id, String typeName) {
        this.number = number;
        this.id = id;
        this.typename = typename;
    }

    public int getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typename;
    }
}


*/
    
        /*
        long id = root.path("id").asLong();
            System.out.println("id : " + id);

        System.out.println(root.get("id").asInt());
        
        // String drone = "{\"id\": 67,\"manufacturer\": \"Altair Aerial\",\"typename\": \"AA108\",\"weight\": 85,\"max_speed\": 36,\"battery_capacity\": 750,\"control_range\": 300,\"max_carriage\": 60}";
          
        Drones drone1Drones = mapper.readValue(new File(jsonFilePath), Drones.class);

        System.out.println(drone1Drones.getId());
*/
}