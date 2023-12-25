/*
 * This class takes control of the whole loop, which means:
 * 1. The loop needs to be done when the program is first started to get names and IDs of the drones
 * that will be showen in the GUI for the User to chose from.
 * 2. After a User choses a drone he wants to get infos of, this class will start the loop on the 
 * precise regulation.
 */

package aControlers;

import Formatter.JsonDemo;
import Formatter.Drones.DroneType1;
import API.Fetcher.APIFetcher;


import java.util.List;


public class MainControler {
    public static void main(String[] args) { 
       // String UrlExt = "51/dynamics/?limit=1&offset=10&format=json";
       String UrlExt = "dronedynamics/?limit=10&offset=10&format=json";
       String FileExt = "Test.json";
        
       String result = APIFetcher.FetchAPI(UrlExt,FileExt);
       // System.out.println(ReadDroneList(Test.json).get(3).getId());
    
       // System.out.println(result);
    
       String JsonFileName = "Test.json";
    
       List<DroneType1> droneList = JsonDemo.ReadDroneList(JsonFileName, 2);

       if (droneList != null && droneList.size() > 3) {
        System.out.println("Battery capacity of the 4th drone: " + droneList.get(5).getBattery_capacity());
        System.out.println("\n\n");
    } else {
        System.out.println("Error reading drone list or the list is empty.");
    }
       
    }

}