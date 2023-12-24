/*
 * This class takes control of the whole loop, which means:
 * 1. The loop needs to be done when the program is first started to get names and IDs of the drones
 * that will be showen in the GUI for the User to chose from.
 * 2. After a User choses a drone he wants to get infos of, this class will start the loop on the 
 * precise regulation.
 */
package Controlers;

import API.Fetcher.APIFetcher;

public class MainControler {
    public static void main(String[] args) { 
       String UrlExt = "51/dynamics/?limit=1&offset=10&format=json";
       String FileExt = "Test.json";
        
       APIFetcher.FetchAPI(UrlExt,FileExt);
        
    }

}