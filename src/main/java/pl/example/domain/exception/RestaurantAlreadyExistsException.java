package pl.example.domain.exception;


public class RestaurantAlreadyExistsException extends RuntimeException {

    public RestaurantAlreadyExistsException(final String message) {
        super(message);
    }
}
