package pl.example.domain.exception;

public class ProcessingException extends RuntimeException {

    public ProcessingException(String message) {
        super(message);
    }
}
