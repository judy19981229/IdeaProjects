package exception;

public class GoodsNotEnoughException extends RuntimeException{
    public GoodsNotEnoughException() {
        super();
    }

    public GoodsNotEnoughException(String message) {
        super(message);
    }
}
