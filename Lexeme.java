import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lexeme {
    // Declare the ArrayList globally
    public static ArrayList<String> allTokens = new ArrayList<>();

    public static void main(String[] args) {
        // Provide the path of your source code file
        String filePath = "C:\\Users\\M.Gulfam\\Desktop\\ToPL_Assignment2\\BadArray.java";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Tokenize the line and identify tokens and lexemes
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    // Output token, lexeme, and line number
                    //System.out.println("Token: " + token + ", Lexeme: " + token + ", Line: " + lineNumber);
                    allTokens.add(token); // Store the token globally
                }
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Print all tokens encountered
        /*allTokens.forEach((n) -> System.out.println(n));*/
        for(int i = 20; i < allTokens.size(); i++){
            System.out.println(allTokens.get(i));
        }        
    }
}
