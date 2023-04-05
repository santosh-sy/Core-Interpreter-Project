import java.util.Scanner;

public class Decl {
    private final Tokenizer tokenizer;

    public Decl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        int curr = tokenizer.getToken();
        if(curr != 4) {
            throw new Exception("Syntax Error!!! Expected: int");
        } else {
            tokenizer.skipToken(); // Consumes int
            IdList idList = new IdList(tokenizer);
            idList.parse();
        }
        curr = tokenizer.getToken();
        if(curr != 12) {
            throw new Exception("Syntax Error!!! Expected: ;");
        } else {
            tokenizer.skipToken(); // Consumes ;
        }
    }

    public void print() {
        int curr = tokenizer.getToken();
        if(curr == 4) {
            System.out.print("int ");
            tokenizer.skipToken(); // Consumes int
            IdList idList = new IdList(tokenizer);
            idList.print();
        }
        curr = tokenizer.getToken();
        if(curr == 12) {
            System.out.println(";");
            tokenizer.skipToken(); // Consumes ;
        }
    }

    public void execute(Scanner reader) {
        tokenizer.skipToken(); // Consume int
        IdList idList = new IdList(tokenizer);
        idList.execute(reader);
        tokenizer.skipToken(); // Consumes ;
    }
}
