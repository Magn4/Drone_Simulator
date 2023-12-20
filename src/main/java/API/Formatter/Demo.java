package API.Formatter;

import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// import java.io.File;
import java.io.IOException;
// import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException {
        // Specify the path to your JSON file
        String jsonFilePath = "/src/main/java/API/Data/DroneTypeList.json";

        // Call the method to read and process the JSON file
        processJsonFile(jsonFilePath);
    }

    private static void processJsonFile(String jsonFilePath) throws IOException {

        // Create a new ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        
        
        // Configure the mapper to use Java arrays for JSON arrays
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);


        IDs[] id = mapper.readValue(jsonFilePath, IDs[].class);

        System.out.println(id);

    }
}

