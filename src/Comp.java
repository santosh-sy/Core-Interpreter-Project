import java.util.Scanner;

public class Comp {
    private final Tokenizer tokenizer;

    public Comp(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        tokenizer.skipToken(); // Consume (
        Op op = new Op(tokenizer);
        op.parse();
        CompOp compOp = new CompOp(tokenizer);
        compOp.parse();
        Op op1 = new Op(tokenizer);
        op1.parse();
        if(tokenizer.getToken() != 21) {
            throw new Exception("Syntax Error!!! Expected: )");
        }
        tokenizer.skipToken();
    }

    public void print() {
        System.out.print("(");
        tokenizer.skipToken(); // Consume (
        Op op = new Op(tokenizer);
        op.print();
        CompOp compOp = new CompOp(tokenizer);
        compOp.print();
        Op op1 = new Op(tokenizer);
        op1.print();
        if(tokenizer.getToken() == 21) {
            System.out.print(") ");
            tokenizer.skipToken();
        }
    }

    public boolean execute(Scanner reader) {
        boolean value = false;
        tokenizer.skipToken(); // Consume (
        Op op = new Op(tokenizer);
        int opVal = op.execute(reader);
        CompOp compOp = new CompOp(tokenizer);
        int val = compOp.execute(reader);
        Op op1 = new Op(tokenizer);
        int op1Val = op1.execute(reader);
        switch (val) {
            case 1 -> value = opVal != op1Val;
            case 2 -> value = opVal == op1Val;
            case 3 -> value = opVal < op1Val;
            case 4 -> value = opVal > op1Val;
            case 5 -> value = opVal <= op1Val;
            case 6 -> value = opVal >= op1Val;
        }
        if(tokenizer.getToken() == 21) {
            tokenizer.skipToken();
        }
        return value;
    }
}
