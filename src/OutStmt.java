import java.util.Scanner;

public class OutStmt {
    private final Tokenizer tokenizer;

    public OutStmt(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        tokenizer.skipToken(); // Consume write
        IdList idList = new IdList(tokenizer);
        idList.parse();
        if(tokenizer.getToken() != 12) {
            throw new Exception("Syntax Error!!! Expected: ;");
        } else {
            tokenizer.skipToken();
        }
    }

    public void print() {
        System.out.print("write ");
        tokenizer.skipToken(); // Consume write
        IdList idList = new IdList(tokenizer);
        idList.print();
        if(tokenizer.getToken() == 12) {
            System.out.println(";");
            tokenizer.skipToken();
        }
    }

    public void execute(Scanner reader) {
        tokenizer.skipToken(); // Consume write
        IdList idList = new IdList(tokenizer);
        int value = idList.execute(reader);
        if(tokenizer.getToken() != 12) {
            tokenizer.skipToken();
        }
        System.out.println(value + " ");
    }
}
