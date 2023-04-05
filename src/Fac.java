public class Fac {
    private final Tokenizer tokenizer;

    public Fac(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        Op op = new Op(tokenizer);
        op.parse();
        if(tokenizer.getToken() == 24) {
            tokenizer.skipToken(); // Consume *
            Fac fac = new Fac(tokenizer);
            fac.parse();
        }
    }

    public void print() {
        Op op = new Op(tokenizer);
        op.print();
        if(tokenizer.getToken() == 24) {
            System.out.print(" * ");
            tokenizer.skipToken(); // Consume *
            Fac fac = new Fac(tokenizer);
            fac.print();
        }
    }

    public static void execute() {

    }
}
