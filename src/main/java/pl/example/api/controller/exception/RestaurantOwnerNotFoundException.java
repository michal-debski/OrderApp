package pl.example.api.controller.exception;

public class RestaurantOwnerNotFoundException extends RuntimeException {
    public RestaurantOwnerNotFoundException(String message) {
        super(message);
    }
}
