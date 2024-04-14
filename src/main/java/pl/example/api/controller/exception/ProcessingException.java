package pl.example.api.controller.exception;

public class ProcessingException extends RuntimeException {

    public ProcessingException(String message) {
        super(message);
    }
}
