import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ErrorLineReader {
    public static void main(String[] args) {
        
        String filePath = "path/to/your/largefile.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("error")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file:");
            e.printStackTrace();
        }
    }
}
