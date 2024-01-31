/*
 * This class takes control of the whole loop, which means:
 * 1. The loop needs to be done when the program is first started to get names and IDs of the drones
 * that will be showen in the GUI for the User to chose from.
 * 2. After a User choses a drone he wants to get infos of, this class will start the loop on the 
 * precise regulation.
 */

package aControlers;

import Formatter.JsonFormatter;
// import Formatter.Drones.DroneType1;
// import Formatter.Drones.DroneType2;
import Formatter.Drones.DroneDynamics;

import API.Fetcher.APIFetcher;


import java.util.List;


public class MainControler {
    public static void main(String[] args) { 
        String UrlExt = "51/dynamics/?limit=1&format=json";
       // String UrlExt = "dronedynamics/?limit=10&offset=10&format=json";
    
      //  String UrlExt = "dronetypes/?format=json";
       // String UrlExt2 = "drones/?format=json";
       // String UrlExt3 = "dronedynamics/?format=json";
        String TOKEN = "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";
        APIFetcher apiFetcher = new APIFetcher();

       String result = apiFetcher.FetchAPI(UrlExt,TOKEN);
       // System.out.println(ReadDroneList(Test.json).get(3).getId());
    
        System.out.println(result);
    
       // String JsonFileName = "Test.json";
    
       List<DroneDynamics> droneList = JsonFormatter.ReadDroneList( 3, result);
       // Type 1: Drone Type List
       // Type 2: Drone List
       // Type 3: Drone Dynamics
    
       System.out.println("Drone ID: " + droneList.get(0).getStatus());

       if (droneList != null && droneList.size() > 3) {
        for (int i = 0; i < droneList.size(); i++) {
            System.out.println("Drone ID: " + droneList.get(i).getNumber());
            System.out.println("Status of the drone: " + droneList.get(i).getStatus());
            System.out.println("\n");
        }
    } else {
        System.out.println("Error reading drone list or the list is empty.");
    }
       
    }

}