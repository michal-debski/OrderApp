package pl.example.api.controller.exception;


public class RestaurantAlreadyExistsException extends RuntimeException {

    public RestaurantAlreadyExistsException(final String message) {
        super(message);
    }
}
