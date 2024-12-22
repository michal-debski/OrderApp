package pl.example.api.controller.exception;

public class OrderException extends RuntimeException {
    public OrderException(final String message) {
        super(message);
    }
}
