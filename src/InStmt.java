import java.util.Scanner;

public class InStmt {
    private final Tokenizer tokenizer;

    public InStmt(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        tokenizer.skipToken(); // Consume read
        IdList idList = new IdList(tokenizer);
        idList.parse();
        if(tokenizer.getToken() != 12) {
            throw new Exception("Syntax Error!!! Expected: ;");
        } else {
            tokenizer.skipToken();
        }
    }

    public void print() {
        System.out.print("read ");
        tokenizer.skipToken(); // Consume read
        IdList idList = new IdList(tokenizer);
        idList.print();
        if(tokenizer.getToken() == 12) {
            System.out.print("; ");
            tokenizer.skipToken();
        }
    }

    public int execute(Scanner reader) {
        tokenizer.skipToken(); // Consume read
        IdList idList = new IdList(tokenizer);
        int val = idList.execute(reader);
        if(tokenizer.getToken() != 12) {
            tokenizer.skipToken();
        }
        return val;
    }
}
