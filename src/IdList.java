import java.util.Scanner;

public class IdList {
    private final Tokenizer tokenizer;

    public IdList(Tokenizer tokenizer1) {
        this.tokenizer = tokenizer1;
    }

    public void parse() throws Exception {
        Id id = new Id(tokenizer);
        id.parse();
        tokenizer.skipToken();
        if(tokenizer.getToken() == 13) {
            tokenizer.skipToken(); // skips ,
            IdList idList = new IdList(tokenizer);
            idList.parse();
        }
    }

    public void print() {
        Id id = new Id(tokenizer);
        id.print();
        tokenizer.skipToken();
        if(tokenizer.getToken() == 13) {
            System.out.print(", ");
            tokenizer.skipToken(); // skips ,
            IdList idList = new IdList(tokenizer);
            idList.print();
        }
    }

    public int execute(Scanner reader) {
        Id id = new Id(tokenizer);
        int value = id.execute();
        tokenizer.skipToken();
        if(tokenizer.getToken() == 13) {
            tokenizer.skipToken(); // skips ,
            IdList idList = new IdList(tokenizer);
            value = idList.execute(reader);
        }
        return value;
    }
}
