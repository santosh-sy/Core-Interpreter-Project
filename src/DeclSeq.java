import java.util.Scanner;

public class DeclSeq {
    private final Tokenizer tokenizer;

    public DeclSeq(Tokenizer tokenizer1) {
        this.tokenizer = tokenizer1;
    }

    public void parse() throws Exception {
        Decl decl = new Decl(tokenizer);
        decl.parse();
        if(tokenizer.getToken() != 2) {
            DeclSeq declSeq = new DeclSeq(tokenizer);
            declSeq.parse();
        }
    }

    public void print() {
        Decl decl = new Decl(tokenizer);
        decl.print();
        if(tokenizer.getToken() != 2) {
            DeclSeq declSeq = new DeclSeq(tokenizer);
            declSeq.print();
        }
    }

    public void execute(Scanner reader) {
        Decl decl = new Decl(tokenizer);
        decl.execute(reader);
        if(tokenizer.getToken() != 2) {
            DeclSeq declSeq = new DeclSeq(tokenizer);
            declSeq.execute(reader);
        }
    }
}
