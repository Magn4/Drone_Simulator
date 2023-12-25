/*
 * Must add a variable which shows us the user choice of type of infos he wants to get.
 * Must add the possibiity of changing the API fetcher URL based on the users choice
 * Must chan
 */

package API.Fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIFetcher {

    public static String FetchAPI(String URLext, String Path) {
        try {
            final String TOKEN = "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";
            
            // Still need to add changable link, based on User request 
        // Done.
            // final URL url = new URL("https://dronesim.facets-labs.com/api/drones/?format=json");

            // String URLext = "51/dynamics";
            // String Path = "Test.json";

            final URL url = new URL("https://dronesim.facets-labs.com/api/" + URLext);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Authorization", TOKEN);
            // to check later
            // con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            reader.close();
           
            // System.out.println("\n\n" + "Response Content: " + responseContent.toString());
            
            // System.out.println("\n\n" + "Changes have been writen to: " + Path + "\n\n");
            // String filePath = "/Users/taha/Desktop/Uni/Java_Project/Drone_Simulator/src/main/java/API/Responses/" + Path;
            
            String filePath = "src/main/java/Data/" + Path;
           
            //System.out.println("\n\n");
            FileWriterUtil.writeToFile(responseContent.toString(), filePath);
            System.out.println("\n\n");
            con.disconnect();
            return responseContent.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

