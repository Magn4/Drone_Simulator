package API.Formatter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        // Specify the path to your JSON file
        String jsonFilePath = "src/main/java/API/Data/DroneTypeList.json";

        // Call the method to read and process the JSON file
        processJsonFile(jsonFilePath);
    }

    private static void processJsonFile(String jsonFilePath) throws IOException {
        // Create a new ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // Configure the mapper to use Java arrays for JSON arrays
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        // Read the JSON data from the file and map it to a wrapper class containing the array
        DroneTypeListWrapper wrapper = mapper.readValue(new File(jsonFilePath), DroneTypeListWrapper.class);

        // Access the array of IDs from the wrapper
        IDs[] ids = wrapper.getResults();

        // Print the array of IDs
        for (IDs id : ids) {
            System.out.println(id);
        }
    }
}
