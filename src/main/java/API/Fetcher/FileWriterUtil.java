package API.Fetcher;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Taha
 */

public class FileWriterUtil {
    public static void writeToFile(String content, String filePath) {

        filePath = "./" + filePath;

        // filePath = "./Logs/" + filePath;

        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
        
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            
            fileWriter.write(formattedDate + "\t\t" + content + "\n");

            // System.out.println("Response written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
