import java.util.Scanner;

public class Program {
    private final Tokenizer tokenizer;

    public Program(Tokenizer tokenizer1) throws Exception {
        this.tokenizer = tokenizer1;
    }

    public void parse() throws Exception {
        if(tokenizer.getToken() != 1) {
            throw new Exception("Syntax Error!!! Expected: program");
        }
        tokenizer.skipToken(); // consume program
        DeclSeq declSeq = new DeclSeq(tokenizer);
        declSeq.parse();
        if(tokenizer.getToken() != 2) {
            throw new Exception("Syntax Error!!! Expected: begin");
        }
        tokenizer.skipToken(); // consume begin
        StmtSeq stmtSeq = new StmtSeq(tokenizer);
        stmtSeq.parse();
        if(tokenizer.getToken() != 3) {
            throw new Exception("Syntax Error!!! Expected: end");
        }
        tokenizer.skipToken(); // consume end
        if(tokenizer.getToken() != 33) {
            throw new Exception("Syntax Error!!! End of Program Unreachable");
        }
    }

    public void print() {
        if(tokenizer.getToken() == 1) {
            System.out.print("program ");
        }
        tokenizer.skipToken(); // consume program
        DeclSeq declSeq = new DeclSeq(tokenizer);
        declSeq.print();
        if(tokenizer.getToken() == 2) {
            System.out.print("begin ");
        }
        tokenizer.skipToken(); // consume begin
        StmtSeq stmtSeq = new StmtSeq(tokenizer);
        stmtSeq.print();
        if(tokenizer.getToken() == 3) {
            System.out.println("end");
        }
        tokenizer.skipToken(); // consume end
    }

    public void execute(Scanner reader) {
        tokenizer.skipToken(); // Consume program
        DeclSeq declSeq = new DeclSeq(tokenizer);
        declSeq.execute(reader);
        tokenizer.skipToken(); // Consume begin
        StmtSeq stmtSeq = new StmtSeq(tokenizer);
        stmtSeq.execute(reader);
        tokenizer.skipToken(); // Consume end
    }
}
