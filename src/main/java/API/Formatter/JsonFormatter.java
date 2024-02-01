package API.Formatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API.Formatter.CreateDrone.droneNode;
import API.Formatter.CreateDrone.droneDynamicsJson;
import API.Formatter.CreateDrone.droneJson;
import API.Formatter.CreateDrone.droneTypeJson;
import API.Formatter.CreateDrone.droneTypeNode;

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
                            T drone = (T) droneTypeJson.DroneTypeFromJsonNode(node, number, mapper);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 2:
                    if (resultsNode.isArray()) {
                        int number = 0;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone = (T) droneNode.DroneFromJsonNode(node, number, mapper);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 3:
                    if (resultsNode.isArray()) {
                        int number = 0;
                        for (JsonNode node : resultsNode) {
                            number++;
                            T drone = (T) droneDynamicsJson.DroneDynamicsFromJsonNode(node, number, mapper);
                            droneList.add(drone);
                        }
                    }
                    break;

                case 4:
                    T drone = (T)  droneJson.DroneFromJsonData(jsonData, mapper);
                    droneList.add(drone);
                    break;

                case 5:
                    T droneType = (T) droneTypeNode.DroneTypeFromJsonData(jsonData, mapper);
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
}
