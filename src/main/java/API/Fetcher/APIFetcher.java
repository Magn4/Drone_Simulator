package API.Fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIFetcher {

    private int responseCode;
    private String responseContent;

    public String FetchAPI(String URL, String TOKEN) {
        try {

            // 6ffe7e815e07b6ede78cade7617454eeb944d168
            // String TOKEN = "Token " + TOKENext;

            final URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Authorization", TOKEN);
            con.setRequestMethod("GET");

            // Get the response code
            responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder responseContentBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseContentBuilder.append(line);
            }

            reader.close();

            // Get the response content
            responseContent = responseContentBuilder.toString();

            con.disconnect();
            return responseContent;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Getter method for response code
    public int getResponseCode() {
        return responseCode;
    }

    // Getter method for response content
    public String getResponseContent() {
        return responseContent;
    }
}
