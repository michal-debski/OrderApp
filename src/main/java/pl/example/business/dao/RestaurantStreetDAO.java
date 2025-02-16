package pl.example.business.dao;

import pl.example.domain.RestaurantStreet;

import java.util.List;

public interface RestaurantStreetDAO {

    List<RestaurantStreet> findAllRestaurantStreets();

    RestaurantStreet saveRestaurantStreet(RestaurantStreet restaurantStreet);

}
