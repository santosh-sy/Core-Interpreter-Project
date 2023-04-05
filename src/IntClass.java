public class IntClass {
    private final Tokenizer tokenizer;

    public IntClass(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() throws Exception {
        if(tokenizer.getToken() != 31) {
            throw new Exception("Syntax Error!!! Expected: Integer");
        }
    }

    public void print() {
        if(tokenizer.getToken() != 31) {
            System.out.print(tokenizer.intVal() + " ");
        }
    }

    public int execute() {
        int val = 0;
        if(tokenizer.getToken() == 31) {
            val = 2;
        }
        return val;
    }
}
