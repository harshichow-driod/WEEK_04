import java.io.*;
import java.util.*;

public class WordFrequencyCounter {

    public static Map<String, Integer> countWordFrequency(String filePath) {
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return wordFrequency;
    }

    public static void main(String[] args) {
        String filePath = "example.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(filePath);
        System.out.println("Word Frequency: " + wordFrequency);
    }
}
