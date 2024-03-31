import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LexicalAnalyzer {
    
    // Keywords in the programming language
    private static final List<String> keywords = Arrays.asList("int", "float", "double", "char", "void", "if", "else", "while", "for", "return");

    public static void main(String[] args) {
        // Provide the path to your source code file here
        String sourceCodeFilePath = "C:\\Users\\M.Gulfam\\Desktop\\ToPL_Assignment2\\BadArray.java";
        analyzeSourceCode(sourceCodeFilePath);
    }

    public static void analyzeSourceCode(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            System.out.printf("%-20s%-20s%-20s%-20s%n", "Lexeme", "Token", "Scope", "Array Size");
            System.out.println("------------------------------------------------------------------------");

            Stack<Character> bracketStack = new Stack<>();
            boolean syntaxError = false;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    if (token.isEmpty()) continue;

                    String lexeme = token;
                    String dataType = "";
                    String scope = ""; // Assuming scope (global/local) needs to be determined here
                    String arraySize = "";

                    if (lexeme.contains(";")) {
                        lexeme = lexeme.replace(";", "");
                    }

                    if (lexeme.equals("{")) {
                        bracketStack.push('{');
                    } else if (lexeme.equals("}")) {
                        if (bracketStack.isEmpty() || bracketStack.pop() != '{') {
                            System.out.println("Syntax error: Extra closing curly bracket '}' on line " + lineNumber);
                            syntaxError = true;
                        }
                    }

                    if (keywords.contains(lexeme)) {
                        dataType = "Keyword";
                    } else {
                        // Assuming all user-defined identifiers are considered as variables
                        dataType = "Identifier";
                        // Here you can add logic to identify data type based on declaration
                    }

                    // Assuming scope determination logic here
                    scope = "Global"; // Example: assuming all are global for now

                    // Assuming array size detection logic here
                    if (lexeme.contains("[")) {
                        int startIndex = lexeme.indexOf("[");
                        int endIndex = lexeme.indexOf("]");
                        if (startIndex != -1 && endIndex != -1) {
                            arraySize = lexeme.substring(startIndex + 1, endIndex);
                            lexeme = lexeme.substring(0, startIndex);
                        }
                    }

                    // Output the lexeme, token, scope, and array size
                    System.out.printf("%-20s%-20s%-20s%-20s%n", lexeme, dataType, scope, arraySize);
                }
            }

            if (!bracketStack.isEmpty()) {
                System.out.println("Syntax error: Missing closing curly bracket '}'");
                syntaxError = true;
            }

            if (!syntaxError) {
                System.out.println("No syntax errors found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
