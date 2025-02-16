package pl.example.business.dao;

import pl.example.domain.Address;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantOwner;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {

    List<Restaurant> findByRestaurantOwnerId(Integer id);



    Optional<Restaurant> findRestaurantById(Integer id);

    List<Restaurant> findAllRestaurants();

    Restaurant saveRestaurant(Restaurant restaurant,  RestaurantOwner restaurantOwner, Address address);

    void deleteRestaurant(Integer restaurantId);


    List<Restaurant> findAllRestaurantByStreetName(String street);
}
