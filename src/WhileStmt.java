import java.util.ArrayList;
import java.util.Scanner;

public class WhileStmt {
    private final Tokenizer tokenizer;

    public WhileStmt(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        tokenizer.skipToken(); // Consume while
        Cond cond = new Cond(tokenizer);
        cond.parse();
        if(tokenizer.getToken() == 9) {
            tokenizer.skipToken(); // Consume loop
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.parse();
            tokenizer.skipToken(); // Consume end
            if(tokenizer.getToken() != 12) {
                throw new Exception("Syntax Error!!! Expected: ;");
            } else {
                tokenizer.skipToken();
            }
        } else {
            throw new Exception("Syntax Error!!! Expected: loop");
        }
    }

    public void print() {
        System.out.print("\nwhile ");
        tokenizer.skipToken(); // Consume while
        Cond cond = new Cond(tokenizer);
        cond.printCond();
        if(tokenizer.getToken() == 9) {
            System.out.print(" loop\n");
            tokenizer.skipToken(); // Consume loop
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.print();
            System.out.print("end");
            tokenizer.skipToken(); // Consume end
            if(tokenizer.getToken() == 12) {
                System.out.println(";");
                tokenizer.skipToken();
            }
        }
    }

    public void execute(Scanner reader) {
        tokenizer.skipToken(); // Consume while
        Cond cond = new Cond(tokenizer);
        boolean value = cond.evalCond(reader);
        if(tokenizer.getToken() == 9) {
            tokenizer.skipToken(); // Consume loop
            while (value){
                StmtSeq stmtSeq = new StmtSeq(tokenizer);
                stmtSeq.execute(reader);
                value = cond.evalCond(reader);
            }
            tokenizer.skipToken(); // Consume end
            if(tokenizer.getToken() != 12) {
                tokenizer.skipToken(); // Consume ;
            }
        }
    }
}
