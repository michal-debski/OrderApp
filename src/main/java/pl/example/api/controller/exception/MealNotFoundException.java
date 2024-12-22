package pl.example.api.controller.exception;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(final String message) {
        super(message);
    }
}

