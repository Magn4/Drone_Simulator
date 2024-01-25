/*
 * I must add the possibility of having more than one result file.
 */
package API.Fetcher;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {
    public static void writeToFile(String content, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
            // System.out.println("Response written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
