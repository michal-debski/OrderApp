package pl.example.business.dao;

import pl.example.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {

    List<Restaurant> findByRestaurantOwnerId(Integer id);

    Optional<Restaurant> findByName(String name);

    Optional<Restaurant> findById(Integer id);

    List<Restaurant> findAll();

    Restaurant saveRestaurant(Restaurant restaurant);

    void deleteRestaurant(Integer restaurantId);

//    List<Restaurant> findByStreet(String street);


}
