package API.Fetcher;

public class URL_Maker {
    

public static String getUrlExtension(String Type, int Offset, int Limit) {
    String URL = "https://dronesim.facets-labs.com/api/";
    switch (Type) {
        case "Drone Type":
            URL = URL + "dronetypes/?format=json";
            break;
        case "Drones":
            URL = URL + "drones/?format=json";
            break;
        case "Drone Dynamics":
            URL = URL + "dronedynamics/?format=json";
            break;
        default:
            return "";
    };
   //  dronedynamics/?limit=25&format=json

    if (Offset != 0){
        URL = URL + "&offset=" + Offset;
    };

    if (Limit != 0){
        URL = URL + "&limit=" + Limit;
    }
    System.out.println("This is the Generated URL: "+URL);
   return URL;
    }
}