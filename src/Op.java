import java.util.Scanner;

public class Op {
    private final Tokenizer tokenizer;

    public Op(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 31 -> {
                IntClass intClass = new IntClass(tokenizer);
                intClass.parse();
                tokenizer.skipToken();
            }
            case 32 -> {
                Id id = new Id(tokenizer);
                id.parse();
                tokenizer.skipToken();
            }
            case 20 -> {
                tokenizer.skipToken(); // Consume (
                Exp exp = new Exp(tokenizer);
                exp.parse();
                if (tokenizer.getToken() == 21) {
                    tokenizer.skipToken(); // Consume )
                } else {
                    throw new Exception("Syntax Error!!! Expected: )");
                }
            }
            default -> throw new Exception("Syntax Error!!! Unexpected Input");
        }
    }

    public void print() {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 31 -> {
                IntClass intClass = new IntClass(tokenizer);
                intClass.print();
                tokenizer.skipToken();
            }
            case 32 -> {
                Id id = new Id(tokenizer);
                id.print();
                tokenizer.skipToken();
            }
            case 20 -> {
                System.out.print("(");
                tokenizer.skipToken(); // Consume (
                Exp exp = new Exp(tokenizer);
                exp.print();
                if (tokenizer.getToken() == 21) {
                    System.out.println(")");
                    tokenizer.skipToken(); // Consume )
                }
            }
        }
    }

    public int execute(Scanner reader) {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 31 -> {
                IntClass intClass = new IntClass(tokenizer);
                return intClass.execute();
            }
            case 32 -> {
                Id id = new Id(tokenizer);
                return id.execute();
            }
            case 20 -> {
                tokenizer.skipToken(); // Consume (
                Exp exp = new Exp(tokenizer);
                int val = exp.execute(reader);
                if (tokenizer.getToken() == 21) {
                    tokenizer.skipToken(); // Consume )
                }
                return val;
            }
        }
        return 0;
    }
}
