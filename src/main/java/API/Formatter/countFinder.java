package API.Formatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API.Fetcher.APIFetcher;
import API.Fetcher.URL_Maker;
import GUI.src.MainPages.aLogin.Login;


/**
 *
 * @author Taha
 */

public class countFinder {
        private static String Token = Login.getToken();


    public static int getCount(String type) {


        APIFetcher apiFetcher = new APIFetcher();

        switch (type) {
            case "Drones":
            String URL = URL_Maker.getUrlExtension("Drones", 0, 0);
            
            String result = apiFetcher.FetchAPI(URL, Token);
    
            ObjectMapper mapper = new ObjectMapper();
            return dronesCountFromJsonNode(result, mapper);

                case "Drone Types":
                String URL2 = URL_Maker.getUrlExtension("Drone Type", 0, 0);
            
                String result2 = apiFetcher.FetchAPI(URL2, Token);
        
                ObjectMapper mapper2 = new ObjectMapper();
                return dronesCountFromJsonNode(result2, mapper2);
            default:
    
        return 0;        }
       
    }

    private static int dronesCountFromJsonNode(String jsonData, ObjectMapper mapper) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonData);
            int count = jsonNode.path("count").asInt();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
