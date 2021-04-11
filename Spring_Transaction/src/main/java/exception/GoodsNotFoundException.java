package exception;

public class GoodsNotFoundException extends RuntimeException{
    public GoodsNotFoundException() {
        super();
    }

    public GoodsNotFoundException(String message) {
        super(message);
    }
}
