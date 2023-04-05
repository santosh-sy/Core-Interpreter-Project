import java.util.Scanner;

public class Exp {
    private final Tokenizer tokenizer;

    public Exp(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        Fac fac = new Fac(tokenizer);
        fac.parse();
        if(tokenizer.getToken() == 22 || tokenizer.getToken() == 23) {
            tokenizer.skipToken(); // Consume + or -
            Exp exp = new Exp(tokenizer);
            exp.parse();
        }
    }

    public void print() {
        Fac fac = new Fac(tokenizer);
        fac.print();
        if(tokenizer.getToken() == 22 || tokenizer.getToken() == 23) {
            if(tokenizer.getToken() == 22) {
                System.out.print(" + ");
            } else if(tokenizer.getToken() == 23) {
                System.out.print(" - ");
            }
            tokenizer.skipToken(); // Consume + or -
            Exp exp = new Exp(tokenizer);
            exp.print();
        }
    }

    public int execute(Scanner reader) {
        return 0;
    }
}
