import java.io.*;
import java.util.*;

/**
 * Tokenizer class for tokenizing an input file
 * into a stream of tokens
 * @author Maggie Feng
 */
public class Tokenizer {
    private final Scanner scanner;
    private final List<String> tokensInLine;
    private String currentLine;
    private int currentIndex;
    /*
    Characters that can have '=' sign follow it
     */
    private static final String EQUAL_SYMBOLS = "=!<>";
    /*
    Represent && symbol
     */
    private static final String AND = "&&";
    /*
    Represent || symbol
     */
    private static final String OR = "||";
    /*
    All symbols that are only 1 character long
     */
    private static final String NON_REPEATING_SYMBOLS = ";,[]()+-*";
    /*
    All whitespace character types
     */
    private static final String WHITESPACE = " \t\n\r";
    /*
    All uppercase letter characters
     */
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /*
    All digits
     */
    private static final String DIGITS = "0123456789";
    private final Map<String, Integer> tokenToCode;

    /**
     * Retrieves the type of token based on the character provided
     * @param character - first character of token
     * @return a String representing the type of token
     */
    private String checkFirstCharacter(char character) {
        if (NON_REPEATING_SYMBOLS.indexOf(character) >= 0) {
            return "terminal";
        }

        if (EQUAL_SYMBOLS.indexOf(character) >= 0) {
            return Character.toString(character);
        }

        if (AND.indexOf(character) >= 0) {
            return "&&";
        }

        if (OR.indexOf(character) >= 0) {
            return "||";
        }

        if (DIGITS.indexOf(character) >= 0) {
            return "digit";
        }

        if (UPPERCASE.indexOf(character) >= 0) {
            return "identifier";
        }

        return switch (character) {
            case 't' -> "then";
            case 'p' -> "program";
            case 'b' -> "begin";
            case 'l' -> "loop";
            case 'r' -> "read";
            case 'e' -> "e";
            case 'i' -> "i";
            case 'w' -> "w";
            default -> "invalid";
        };

    }

    /**
     * Checks if newToken is a keyword or not
     * @param type the expected token
     * @param newToken the token to check
     * @param i the current index in the current line
     * @return 1 if newToken is a valid keyword, 0 if newToken is invalid token, or -1 if cannot tell
     * if newToken is valid or invalid yet
     */
    private int checkKeyword(String type, String newToken, int i) {

        if (newToken.equals(type)) {
            int nextIdx = i + 1;
            int nextNextIdx = i + 2;

            if (nextIdx < this.currentLine.length() &&
                    (WHITESPACE.indexOf(this.currentLine.charAt(nextIdx)) >= 0 || NON_REPEATING_SYMBOLS.indexOf(this.currentLine.charAt(nextIdx)) >= 0 ||
                            EQUAL_SYMBOLS.indexOf(this.currentLine.charAt(nextIdx)) >= 0)) {
                this.tokensInLine.add(newToken);

                return 1;
            } else if (nextIdx < this.currentLine.length() && nextNextIdx < this.currentLine.length()) {
                String next = "" + this.currentLine.charAt(nextIdx) + this.currentLine.charAt(nextNextIdx);
                if (next.equals("&&") || next.equals("||")) {
                    this.tokensInLine.add(newToken);
                    return 1;
                }
            } else if (nextIdx >= this.currentLine.length()) {
                this.tokensInLine.add(newToken);
                return 1;
            } else {
                this.tokensInLine.add("invalid");
                return 0;
            }
        }
        return -1;

    }

    /**
     * Checks if the there exists whitespace or special symbols in the next two indices
     * after the current index, i
     * @param i the current index
     * @return true if there is whitespace or special symbols following the current index i. Otherwise, false.
     */
    private boolean checkWhiteSpace(int i) {

        int nextIdx = i + 1;
        int nextNextIdx = i + 2;

        if (nextIdx < this.currentLine.length() &&
                (WHITESPACE.indexOf(this.currentLine.charAt(nextIdx)) >= 0 || NON_REPEATING_SYMBOLS.indexOf(this.currentLine.charAt(nextIdx)) >= 0 ||
                        EQUAL_SYMBOLS.indexOf(this.currentLine.charAt(nextIdx)) >= 0)) {
            return true;
        } else if (nextIdx < this.currentLine.length() && nextNextIdx < this.currentLine.length()) {
            String next = "" + this.currentLine.charAt(nextIdx) + this.currentLine.charAt(nextNextIdx);
            return next.equals("&&") || next.equals("||");
        } else return nextIdx >= this.currentLine.length();

    }

    /**
     * Checks if token + c is a valid special symbol.
     * @param type the expected token
     * @param token the current token
     * @param c the character to add to the current token
     * @return int > 0 if the token + c is valid token. Otherwise, 0 if token + c is invalid
     */
    private int checkSymbol(String type, String token, char c) {
        String newToken = token + c;

        if (type.equals("&&") || type.equals("||")) {
            if (newToken.equals(type)) {
                this.tokensInLine.add(newToken);
                return 2;
            }
            this.tokensInLine.add("invalid");
            return 0;
        }

        if (EQUAL_SYMBOLS.contains(type)) {
            if (c == '=') {
                this.tokensInLine.add(newToken);
                return 2;
            } else {
                this.tokensInLine.add(token);
                return 1;
            }
        }

        return 0;

    }

    /**
     * Reads the next line in the input buffer and
     * generates a list of tokens from that line
     */
    private void readLine() {
        this.currentLine = this.scanner.nextLine();

        this.tokensInLine.clear();
        this.currentIndex = 0;

        String token = "";
        String type = "";

        for (int i = 0; i < this.currentLine.length(); i++) {
            char character = this.currentLine.charAt(i);

            boolean isWhitespace = WHITESPACE.indexOf(character) >= 0;

            if (!isWhitespace) {
                if (token.length() == 0) {
                    type = this.checkFirstCharacter(character);
                    if (type.equals("invalid")) {
                        this.tokensInLine.add(type);
                        return;
                    }
                    if (type.equals("terminal")) {
                        this.tokensInLine.add("" + character);
                        continue;
                    }
                    token += character;
                } else if (type.equals("e")) {
                    if (character == 'n') {
                        type = "end";
                        token += character;
                    } else if (character == 'l') {
                        type = "else";
                        token += character;
                    } else {
                        this.tokensInLine.add("invalid");
                        return;
                    }
                } else if (type.equals("i")) {
                    if (character == 'n') {
                        type = "int";
                        token += character;
                    } else if (character == 'f') {
                        type = "if";
                        token += character;
                        int status = checkKeyword(type, token, i);
                        if (status == 1) {
                            token = "";
                        } else if (status == 0) {
                            return;
                        }
                    } else {
                        this.tokensInLine.add("invalid");
                        return;
                    }
                } else if (type.equals("w")) {
                    if (character == 'h') {
                        type = "while";
                        token += character;
                    } else if (character == 'r') {
                        type = "write";
                        token += character;
                    } else {
                        this.tokensInLine.add("invalid");
                        return;
                    }
                } else {

                    switch (type) {
                        case "then":
                        case "program":
                        case "begin":
                        case "loop":
                        case "read":
                        case "end":
                        case "else":
                        case "int":
                        case "write":
                        case "while":
                            String newToken = token + character;
                            if (type.startsWith(newToken)) {
                                int status = checkKeyword(type, newToken, i);
                                if (status == 1) {
                                    token = "";
                                } else if (status == 0) {
                                    return;
                                } else {
                                    token = newToken;
                                }
                            } else {
                                this.tokensInLine.add("invalid");
                                return;
                            }
                            break;
                        case "=":
                        case "!":
                        case "<":
                        case ">":
                        case "&&":
                        case "||":
                            int status = checkSymbol(type, token, character);
                            if (status == 2) {
                                token = "";
                            } else if (status == 1) {
                                type = checkFirstCharacter(character);
                                if (type.equals("invalid")) {
                                    this.tokensInLine.add(type);
                                    return;
                                }
                                if (type.equals("terminal")) {
                                    this.tokensInLine.add("" + character);
                                    token = "";
                                    continue;
                                }
                                token = "" + character;
                            } else {
                                return;
                            }
                            break;
                        case "digit":
                            newToken = token + character;
                            if (!this.isInteger(newToken)) {
                                if (checkWhiteSpace(i-1)) {
                                    this.tokensInLine.add(token);
                                    if (WHITESPACE.indexOf(character) >= 0) {
                                        token = "";

                                    } else {
                                        type = checkFirstCharacter(character);
                                        if (type.equals("invalid")) {
                                            this.tokensInLine.add(type);
                                            return;
                                        }
                                        if (type.equals("terminal")) {
                                            this.tokensInLine.add("" + character);
                                            token = "";
                                            continue;
                                        }
                                        token = "" + character;
                                    }

                                } else {
                                    this.tokensInLine.add("invalid");
                                    return;
                                }
                            } else {
                                token = newToken;
                            }
                            break;
                        case "identifier":
                            newToken = token + character;
                            if (!this.isIdentifier(newToken)) {
                                if (checkWhiteSpace(i-1)) {
                                    this.tokensInLine.add(token);
                                    if (WHITESPACE.indexOf(character) >= 0) {
                                        token = "";
                                    } else {
                                        type = checkFirstCharacter(character);
                                        if (type.equals("invalid")) {
                                            this.tokensInLine.add(type);
                                            return;
                                        }
                                        if (type.equals("terminal")) {
                                            this.tokensInLine.add("" + character);
                                            token = "";
                                            continue;
                                        }
                                        token = "" + character;
                                    }
                                } else {
                                    this.tokensInLine.add("invalid");
                                    return;
                                }
                            } else {
                                token = newToken;
                            }
                            break;
                    }
                }

            } else {
                if (token.length() > 0) {
                    if (this.tokenToCode.containsKey(token) || this.isInteger(token) || this.isIdentifier(token)) {
                        this.tokensInLine.add(token);
                        token = "";
                    } else {
                        this.tokensInLine.add("invalid");
                        return;
                    }
                }

            }

        }

        if (token.length() > 0) {
            this.tokensInLine.add(token);
        }
    }

    public Tokenizer(String fileName) throws FileNotFoundException {
        this.scanner = new Scanner(new File(fileName));
        this.tokensInLine = new ArrayList<>();
        this.currentLine = "";
        this.currentIndex = 0;

        // Map token strings to token numbers
        this.tokenToCode = new HashMap<>();
        this.tokenToCode.put("program", 1);
        this.tokenToCode.put("begin", 2);
        this.tokenToCode.put("end", 3);
        this.tokenToCode.put("int", 4);
        this.tokenToCode.put("if", 5);
        this.tokenToCode.put("then", 6);
        this.tokenToCode.put("else", 7);
        this.tokenToCode.put("while", 8);
        this.tokenToCode.put("loop", 9);
        this.tokenToCode.put("read", 10);
        this.tokenToCode.put("write", 11);
        this.tokenToCode.put(";", 12);
        this.tokenToCode.put(",", 13);
        this.tokenToCode.put("=", 14);
        this.tokenToCode.put("!", 15);
        this.tokenToCode.put("[", 16);
        this.tokenToCode.put("]", 17);
        this.tokenToCode.put("&&", 18);
        this.tokenToCode.put("||", 19);
        this.tokenToCode.put("(", 20);
        this.tokenToCode.put(")", 21);
        this.tokenToCode.put("+", 22);
        this.tokenToCode.put("-", 23);
        this.tokenToCode.put("*", 24);
        this.tokenToCode.put("!=", 25);
        this.tokenToCode.put("==", 26);
        this.tokenToCode.put("<", 27);
        this.tokenToCode.put(">", 28);
        this.tokenToCode.put("<=", 29);
        this.tokenToCode.put(">=", 30);

        // Read and tokenize first line in the input file
        this.skipToken();
    }

    /**
     * Retrieves the token number of the current token if it exists
     * @return token number of current token, 33 if at EOF, or 34 for invalid token
     */
    public int getToken() {
        // If input buffer is at EOF
        if (this.currentIndex == -1) {
            return 33;
        }

        String token = this.tokensInLine.get(this.currentIndex);

        // If token is a keyword or symbol
        if (this.tokenToCode.containsKey(token)) {
            return this.tokenToCode.get(token);
        }

        // If token is an integer
        if (this.isInteger(token)) {
            return 31;
        }

        // If token is an identifier
        if (this.isIdentifier(token)) {
            return 32;
        }

        // Token is invalid
        return 34;
    }

    /**
     * Retrieves the next token if one exists
     */
    public void skipToken() {
        /*
            Increment the current index to point to a new token in
            the current line if possible
         */
        if (this.currentIndex < this.tokensInLine.size() - 1) {
            this.currentIndex++;
        }else {
            /*
                Otherwise read the next line to get the next token
             */
            while(this.scanner.hasNextLine()) {
                this.readLine();
                if (!this.currentLine.equals("")) {
                    return;
                }
            }
            this.currentIndex = -1;
        }
    }

    /**
     * Returns the value of an identifier token or null if token is not an identifier
     * @return String value of current token if it is identifier. Otherwise, null.
     */
    public String idName() {
        if (this.currentIndex == -1 || this.tokensInLine.size() == 0) {
            System.out.println("Current Token is not a valid identifier");
            return null;
        }

        String token = this.tokensInLine.get(this.currentIndex);

        if (!this.isIdentifier(token)) {
            System.out.println("Current Token is not a valid identifier");
            return null;
        }

        return token;
    }

    /**
     * Returns the value of an integer token, or -1 if token is not an integer
     * @return integer value if current token is integer. Otherwise -1.
     */
    public int intVal() {
        if (this.currentIndex == -1 || this.tokensInLine.size() == 0) {
            System.out.println("Current Token is not a valid unsigned integer");
            return -1;
        }

        String token = this.tokensInLine.get(this.currentIndex);
        if (!this.isInteger(token)) {
            System.out.println("Current Token is not a valid unsigned integer");
            return -1;
        }

        return Integer.parseUnsignedInt(token);
    }

    /**
     * Closes scanner
     */
    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Gets the current input line
     * @return the current input line
     */
    public String getLine() {
        return this.currentLine;
    }

    /**
     * Returns true if token is an unsigned integer. Otherwise returns false.
     * @param token token String
     * @return true if token is made up of only digits. Otherwise, false.
     */
    private boolean isInteger(String token) {
        for (char c : token.toCharArray())
        {
            if (DIGITS.indexOf(c) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if token is a valid identifier. Otherwise returns false.
     * @param token token String
     * @return true if token starts with uppercase letter followed by zero or more
     * digits or uppercase letters. Otherwise, false.
     */
    public boolean isIdentifier(String token) {
        if (DIGITS.indexOf(token.charAt(0)) >= 0
                || UPPERCASE.indexOf(token.charAt(0)) < 0) {
            return false;
        }
        for (char c : token.toCharArray())
        {
            if (DIGITS.indexOf(c) < 0 && UPPERCASE.indexOf(c) < 0) {
                return false;
            }
        }
        return true;
    }


    //public static void main(String[] args) {
        /*if (args.length != 1) {
            System.err.println("Invalid Arguments: Expected 'java Tokenizer <fileName>' ");
            return;
        }*/
        /*String fileName = "src/test.txt";
        Tokenizer tokenizer;

        // Try to initialize Tokenizer class
        try {
            tokenizer = new Tokenizer(fileName);
        } catch (FileNotFoundException e) {
            System.err.printf("File '%s' does not exist%n", fileName);
            return;
        }

        boolean invalidToken = false;

        // Loop through input and print token numbers
        while (tokenizer.getToken() != 33 && !invalidToken) {
            int num = tokenizer.getToken();
            if (num == 34) {
                System.out.println("Invalid token");
                System.out.println("Error at line: " + tokenizer.getLine());
                invalidToken = true;
            } else {
                System.out.println(num);
                tokenizer.skipToken();
            }
        }

        // print EOF token
        if (!invalidToken) {
            System.out.println(tokenizer.getToken());
        }

        // Close scanner after done using
        tokenizer.closeScanner();
    }*/
}
