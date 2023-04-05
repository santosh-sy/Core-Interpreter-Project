import java.util.Scanner;

public class CompOp {

    private final Tokenizer tokenizer;

    public CompOp(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        int curr = tokenizer.getToken();
        if(curr != 25 && curr != 26 && curr != 27 && curr != 28 && curr != 29 && curr != 30) {
            throw new Exception("Syntax Error!!! Expected: Operator");
        }
        tokenizer.skipToken();
    }

    public void print() {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 25 -> System.out.print(" != ");
            case 26 -> System.out.print(" == ");
            case 27 -> System.out.print(" < ");
            case 28 -> System.out.print(" > ");
            case 29 -> System.out.print(" <= ");
            case 30 -> System.out.print(" >= ");
        }
        tokenizer.skipToken();
    }

    public int execute(Scanner reader) {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 25 -> { // !=
                return 1;
            }
            case 26 -> {
                return 2; // ==
            }
            case 27 -> {
                return 3; // <
            }
            case 28 -> {
                return 4; // >
            }
            case 29 -> {
                return 5; // <=
            }
            case 30 -> {
                return 6; // >=
            }
        }
        tokenizer.skipToken();
        return 0;
    }
}
