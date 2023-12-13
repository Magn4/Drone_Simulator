package Code.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIFetcher {
    public static void main(String[] args) {
        try {
            final String TOKEN = "Token 6ffe7e815e07b6ede78cade7617454eeb944d168";
            final URL url = new URL("https://dronesim.facets-labs.com/api/dronedynamics/?format=json");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Authorization", TOKEN);
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response content
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            reader.close();

            // Print the response content
            System.out.println("Response Content: " + responseContent.toString());

            // Specify the full file path where you want the file to be created
            String filePath = "/Users/taha/Desktop/Uni/Java_Project/Drone_Simulator/Code/API/Responses/response.json";

            // Use the FileWriterUtil to write the response content to the specified file
            FileWriterUtil.writeToFile(responseContent.toString(), filePath);

            // Disconnect the connection
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
