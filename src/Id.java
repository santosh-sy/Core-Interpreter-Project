public class Id {
    private final Tokenizer tokenizer;

    public Id(Tokenizer tokenizer1) {
        this.tokenizer = tokenizer1;
    }

    public void parse() throws Exception {
        if(tokenizer.getToken() != 32) {
            throw new Exception("Syntax Error!!! Expected: identifier");
        }
    }

    public void print() {
        if(tokenizer.getToken() == 32) {
            System.out.print(tokenizer.idName());
        }
    }

    public int execute() {
        int value = 0;
        if(tokenizer.getToken() == 32) {
            tokenizer.intVal();
        }
        return value;
    }
}
