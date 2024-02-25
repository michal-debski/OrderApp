package pl.example.business.dao;

import pl.example.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {

    Optional<Restaurant> findByName(String name);

    List<Restaurant> findAll();

    Restaurant saveRestaurant(Restaurant restaurant);

    void deleteRestaurant(Restaurant restaurant);

//    List<Restaurant> findByStreet(String street);



}
