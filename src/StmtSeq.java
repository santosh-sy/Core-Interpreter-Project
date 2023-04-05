import java.util.Scanner;

public class StmtSeq {
    private final Tokenizer tokenizer;

    public StmtSeq(Tokenizer tokenizer1) {
        this.tokenizer = tokenizer1;
    }

    public void parse() throws Exception {
        Stmt stmt = new Stmt(tokenizer);
        stmt.parse();
        if(tokenizer.getToken() != 3 && tokenizer.getToken() != 7) {
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.parse();
        }
    }

    public void print() {
        Stmt stmt = new Stmt(tokenizer);
        stmt.print();
        if(tokenizer.getToken() != 3 && tokenizer.getToken() != 7) {
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.print();
        }
    }

    public void execute(Scanner reader) {
        Stmt stmt = new Stmt(tokenizer);
        stmt.execute(reader);
        if(tokenizer.getToken() != 3 && tokenizer.getToken() != 7) {
            StmtSeq stmtSeq = new StmtSeq(tokenizer);
            stmtSeq.execute(reader);
        }
    }
}
