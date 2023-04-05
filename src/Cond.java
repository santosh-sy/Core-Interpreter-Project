import java.util.Scanner;

public class Cond {
    private final Tokenizer tokenizer;

    public Cond(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 20 -> {
                Comp comp = new Comp(tokenizer);
                comp.parse();
            }
            case 15 -> {
                tokenizer.skipToken(); // Consume !
                Cond cond = new Cond(tokenizer);
                cond.parse();
            }
            case 16 -> {
                tokenizer.skipToken(); // Consume [
                Cond cond1 = new Cond(tokenizer);
                cond1.parse();
                if (tokenizer.getToken() != 18 && tokenizer.getToken() != 19) {
                    throw new Exception("Syntax Error!!! Expected: && or ||");
                }
                tokenizer.skipToken(); // Consume && or ||
                Cond cond2 = new Cond(tokenizer);
                cond2.parse();
                if (tokenizer.getToken() != 17) {
                    throw new Exception("Syntax Error!!! Expected: ]");
                }
                tokenizer.skipToken(); // Consume ]
            }
            default -> throw new Exception("Syntax Error!!! Unexpected Value found");
        }
    }

    public void printCond() {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 20 -> {
                Comp comp = new Comp(tokenizer);
                comp.print();
            }
            case 15 -> {
                System.out.print("!");
                tokenizer.skipToken(); // Consume !
                Cond cond = new Cond(tokenizer);
                cond.printCond();
            }
            case 16 -> {
                System.out.print("[");
                tokenizer.skipToken(); // Consume [
                Cond cond1 = new Cond(tokenizer);
                cond1.printCond();
                if (tokenizer.getToken() == 18) {
                    System.out.print(" && ");
                } else if (tokenizer.getToken() == 19) {
                    System.out.print(" || ");
                }
                tokenizer.skipToken(); // Consume && or ||
                Cond cond2 = new Cond(tokenizer);
                cond2.printCond();
                if (tokenizer.getToken() == 17) {
                    System.out.println("]");
                    tokenizer.skipToken(); // Consume ]
                }
            }
        }
    }

    public boolean evalCond(Scanner reader) {
        int curr = tokenizer.getToken();
        switch (curr) {
            case 20 -> {
                Comp comp = new Comp(tokenizer);
                return comp.execute(reader);
            }
            case 15 -> {
                tokenizer.skipToken(); // Consume !
                Cond cond = new Cond(tokenizer);
                boolean val = cond.evalCond(reader);
                return !val;
            }
            case 16 -> {
                tokenizer.skipToken(); // Consume [
                int temp = 0;
                Cond cond1 = new Cond(tokenizer);
                boolean bool1 = cond1.evalCond(reader);
                if (tokenizer.getToken() == 18) {
                    temp = 2;
                } else if (tokenizer.getToken() == 19) {
                    temp = 3;
                }
                tokenizer.skipToken(); // Consume && or ||
                Cond cond2 = new Cond(tokenizer);
                boolean bool2 = cond2.evalCond(reader);
                if (tokenizer.getToken() == 17) {
                    tokenizer.skipToken(); // Consume ]
                }
                if (temp == 2) {
                    return bool1 && bool2;
                } else if (temp == 3) {
                    return bool1 || bool2;
                }
            }
        }
        return false;
    }
}
