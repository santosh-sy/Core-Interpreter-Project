import java.util.Scanner;

public class Stmt {
    private final Tokenizer tokenizer;

    public Stmt(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 5 -> {
                IfStmt ifStmt = new IfStmt(tokenizer);
                ifStmt.parse();
            }
            case 8 -> {
                WhileStmt whileStmt = new WhileStmt(tokenizer);
                whileStmt.parse();
            }
            case 10 -> {
                InStmt inStmt = new InStmt(tokenizer);
                inStmt.parse();
            }
            case 11 -> {
                OutStmt outStmt = new OutStmt(tokenizer);
                outStmt.parse();
            }
            case 32 -> {
                AssignStmt assignStmt = new AssignStmt(tokenizer);
                assignStmt.parse();
            }
            default -> throw new Exception("Syntax Error!!! Unexpected value found");
        }
    }

    public void print() {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 5 -> {
                IfStmt ifStmt = new IfStmt(tokenizer);
                ifStmt.printIf();
            }
            case 8 -> {
                WhileStmt whileStmt = new WhileStmt(tokenizer);
                whileStmt.print();
            }
            case 10 -> {
                InStmt inStmt = new InStmt(tokenizer);
                inStmt.print();
            }
            case 11 -> {
                OutStmt outStmt = new OutStmt(tokenizer);
                outStmt.print();
            }
            case 32 -> {
                AssignStmt assignStmt = new AssignStmt(tokenizer);
                assignStmt.print();
            }
        }
    }

    public void execute(Scanner reader) {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 5 -> {
                IfStmt ifStmt = new IfStmt(tokenizer);
                ifStmt.executeIf(reader);
            }
            case 8 -> {
                WhileStmt whileStmt = new WhileStmt(tokenizer);
                whileStmt.execute(reader);
            }
            case 10 -> {
                InStmt inStmt = new InStmt(tokenizer);
                inStmt.execute(reader);
            }
            case 11 -> {
                OutStmt outStmt = new OutStmt(tokenizer);
                outStmt.execute(reader);
            }
            case 32 -> {
                AssignStmt assignStmt = new AssignStmt(tokenizer);
                assignStmt.execute(reader);
            }
        }
    }
}
