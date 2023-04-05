import java.util.Scanner;

public class AssignStmt {
    private final Tokenizer tokenizer;

    public AssignStmt(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        tokenizer.skipToken(); // Consume identifier
        if(tokenizer.getToken() != 14) {
            throw new Exception("Syntax Error!!! Expected: =");
        } else {
            tokenizer.skipToken(); // Consume =
        }
        Exp exp = new Exp(tokenizer);
        exp.parse();
        if(tokenizer.getToken() != 12) {
            throw new Exception("Syntax Error!!! Expected: ;");
        } else {
            tokenizer.skipToken(); // Consume ;
        }
    }

    public void print() {
        System.out.print(tokenizer.idName() + " ");
        tokenizer.skipToken(); // Consume identifier
        if(tokenizer.getToken() == 14) {
            System.out.print(" = ");
            tokenizer.skipToken(); // Consume =
        }
        Exp exp = new Exp(tokenizer);
        exp.print();
        if(tokenizer.getToken() == 12) {
            System.out.println(";");
            tokenizer.skipToken(); // Consume ;
        }
    }

    public void execute(Scanner reader) {
        tokenizer.skipToken(); // Consume identifier
        if(tokenizer.getToken() != 14) {
            tokenizer.skipToken(); // Consume =
        }
        Exp exp = new Exp(tokenizer);
        exp.execute(reader);
        if(tokenizer.getToken() != 12) {
            tokenizer.skipToken(); // Consume ;
        }
    }
}
