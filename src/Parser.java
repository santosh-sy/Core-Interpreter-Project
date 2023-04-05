import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Parser {

    public static void main(String[] args) throws Exception {
        Tokenizer tokenizer = new Tokenizer("src/test.txt");
        Program program = new Program(tokenizer);
        program.parse();
        Tokenizer tokenizer1 = new Tokenizer("src/test.txt");
        Program program1 = new Program(tokenizer1);
        program1.print();
        Tokenizer tokenizer2 = new Tokenizer("src/test.txt");
        Program program2 = new Program(tokenizer2);
        Scanner reader = null;
        try {
            File dataFile = new File("src/data.txt");
            reader = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}
