import java.util.Scanner;

public class IfStmt {
    private final Tokenizer tokenizer;

    public IfStmt(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        tokenizer.skipToken(); // Consume if
        Cond cond = new Cond(tokenizer);
        cond.parse();
        if(tokenizer.getToken() == 6) {
            tokenizer.skipToken(); // Consume then
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.parse();
            if(tokenizer.getToken() == 7) {
                tokenizer.skipToken(); // Consume else
                StmtSeq elseStmtSeq = new StmtSeq(tokenizer);
                elseStmtSeq.parse();
            }
            tokenizer.skipToken(); // Consume end
            if(tokenizer.getToken() != 12) {
                throw new Exception("Syntax Error!!! Expected: ;");
            } else {
                tokenizer.skipToken(); // Consume ;
            }
        } else {
            throw new Exception("Syntax Error!!! Expected; then");
        }
    }

    public void printIf() {
        System.out.print("if ");
        tokenizer.skipToken(); // Consume if
        Cond cond = new Cond(tokenizer);
        cond.printCond();
        if(tokenizer.getToken() == 6) {
            System.out.print("then ");
            tokenizer.skipToken(); // Consume then
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.print();
            if(tokenizer.getToken() == 7) {
                System.out.print("else ");
                tokenizer.skipToken(); // Consume else
                StmtSeq elseStmtSeq = new StmtSeq(tokenizer);
                elseStmtSeq.print();
            }
            System.out.print("end");
            tokenizer.skipToken(); // Consume end
            if(tokenizer.getToken() == 12) {
                System.out.println(";");
                tokenizer.skipToken(); // Consume ;
            }
        }
    }

    public void executeIf(Scanner reader) {
        tokenizer.skipToken(); // Consume if
        Cond cond = new Cond(tokenizer);
        cond.evalCond(reader);
        if(tokenizer.getToken() == 6) {
            tokenizer.skipToken(); // Consume then
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.execute(reader);
            if(tokenizer.getToken() == 7) {
                tokenizer.skipToken(); // Consume else
                StmtSeq elseStmtSeq = new StmtSeq(tokenizer);
                elseStmtSeq.execute(reader);
            }
            tokenizer.skipToken(); // Consume end
            if(tokenizer.getToken() == 12) {
                tokenizer.skipToken(); // Consume ;
            }
        }
    }
}
