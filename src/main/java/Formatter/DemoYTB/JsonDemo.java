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

            String jsonFileName = "DroneTypeList.json";
            String jsonFilePath = "src/main/java/API/Data/" + jsonFileName;

            JsonNode root = mapper.readTree(new File(jsonFilePath));

            JsonNode ResultsNode = root.path("results");
            if (ResultsNode.isArray()) {
                System.out.println("Is this node an Array? " + ResultsNode.isArray());

                // Create a list to store Drone objects
                List<Drone> droneList = new ArrayList<>();

                int number = 0;
                for (JsonNode node : ResultsNode) {
                    number++;
                    String droneId = node.path("Id").asText();
                    String typeName = node.path("typename").asText();
                    System.out.println("type : " + droneId);
                    System.out.println("ref : " + typeName);

                    // Create a new Drone object and add it to the list
                    Drone drone = new Drone(number, droneId, typeName);
                    droneList.add(drone);
                }

                // Now you can access your drones using the list
                for (Drone drone : droneList) {
                    System.out.println("Drone " + drone.getNumber() + ": " +
                            "Type = " + drone.getDroneId() + ", Ref = " + drone.getTypeName());
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
}

class Drone {
    private int number;
    private String droneId;
    private String typeName;

    public Drone(int number, String droneId, String typeName) {
        this.number = number;
        this.droneId = droneId;
        this.typeName = typeName;
    }

    public int getNumber() {
        return number;
    }

    public String getDroneId() {
        return droneId;
    }

    public String getTypeName() {
        return typeName;
    }
}



    
        /*
        long id = root.path("id").asLong();
            System.out.println("id : " + id);

        System.out.println(root.get("id").asInt());
        
        // String drone = "{\"id\": 67,\"manufacturer\": \"Altair Aerial\",\"typename\": \"AA108\",\"weight\": 85,\"max_speed\": 36,\"battery_capacity\": 750,\"control_range\": 300,\"max_carriage\": 60}";
          
        Drones drone1Drones = mapper.readValue(new File(jsonFilePath), Drones.class);

        System.out.println(drone1Drones.getId());
*/