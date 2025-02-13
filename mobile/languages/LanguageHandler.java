package mobile.languages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanguageHandler {
    List<String> dictionary = new ArrayList<String>();

    public void changeLanguage(String filename){
        dictionary.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < 30; i++) {
                String line = br.readLine();
                if (line != null) dictionary.add(line);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void fromDictionary(int index){
        System.out.println(dictionary.get(index));
    }

    public void fromDictionaryNoNewline(int index){
        System.out.print(dictionary.get(index));
    }
}
