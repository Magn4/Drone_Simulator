/*
 * Must add a variable which shows us the user choice of type of infos he wants to get.
 * Must add the possibiity of changing the API fetcher URL based on the users choice
 * 
 */

package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIFetcher {
    public static void main(String[] args) {
        try {
            final String TOKEN = "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";
            
            // Still need to add changable link, based on User request
            // final URL url = new URL("https://dronesim.facets-labs.com/api/drones/?format=json");

            final URL url = new URL("https://dronesim.facets-labs.com/api/dronetypes/67/?format=json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Authorization", TOKEN);
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

            System.out.println("Response Content: " + responseContent.toString());

            String filePath = "/Users/taha/Desktop/Uni/Java_Project/Drone_Simulator/src/main/java/API/Responses/response.json";

            FileWriterUtil.writeToFile(responseContent.toString(), filePath);

            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
