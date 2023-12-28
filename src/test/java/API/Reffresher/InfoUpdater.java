/* This class needs to get the latest info stored in the LatestInfo.json file and compare it with infos
* that will be fetched from the DroneDynamics Endpoint: "https://dronesim.facets-labs.com/api/dronedynamics/?limit=10"
* and see if there is any changes, and update the LatestInfo.json file. This Class needs to be run every
* 1 minute (This time needs to be updated based on a test, of at what tempo does the data update, 
* that still needs to be done).
*/
/* It reads data from a local JSON file, fetches data from an API endpoint, compares 
the two datasets, updates the local JSON file if changes are detected, and displays updates.  */

package API.Refresh;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class InfoUpdater{
    
    private static final String JSON_FILE_PATH = "LatestInfo.json";
    private static final String API_ENDPOINT = "https://dronesim.facets-labs.com/api/dronedynamics/?limit=10";

    public static void main(String[] args) {
        try {
            // Read data from LatestInfo.json
            JsonNode latestInfo = readJsonFromFile(JSON_FILE_PATH);
            // Fetch data from the API
            JsonNode apiData = fetchDataFromApi(API_ENDPOINT);
            // Compare data and update LatestInfo.json if there are changes
            if (differs(latestInfo, apiData)) {
                // Update the LatestInfo.json file
                writeJsonToFile(apiData, JSON_FILE_PATH);
                System.out.println("Data updated in LatestInfo.json");
                displayUpdates(apiData);
            } 
            else {
                System.out.println("No updates");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Perform data refresh (you can customize this method)
    public void performRefresh() {
        System.out.println("Performing data refresh...");
        // Add your logic here to refresh the data
        // For example, you can call the main method or perform specific actions
        main(new String[]{});
    }

private static boolean differs(JsonNode latestInfo, JsonNode apiData) {
        return false;
    }

//this method is used to read JSON data from a file given its file path. 
//It uses the Jackson library to parse the JSON content and returns a JsonNode
   private static JsonNode readJsonFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(new File(filePath));
    }
/*this method is used to make a network call to the specified API endpoint,
 retrieve JSON data, and parse it into a JsonNode using the Jackson library. */
    private static JsonNode fetchDataFromApi(String apiUrl) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(new URL(apiUrl));
    }

/*this method provides a convenient way to persist JSON data to a file. It uses the Jackson library (ObjectMapper)
 to convert a JsonNode object into its JSON representation and writes it to the specified file. */
    private static void writeJsonToFile(JsonNode data, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), data);
    }
/*this method serves the purpose of providing a simple way to display updates
 in a human-readable format */
    private static void displayUpdates(JsonNode updates) {
        System.out.println("Updates:\n" + updates.toPrettyString());
    }
}
