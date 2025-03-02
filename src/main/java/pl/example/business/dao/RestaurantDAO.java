package pl.example.business.dao;

import pl.example.domain.Address;
import pl.example.domain.Restaurant;
import pl.example.domain.User;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {

    List<Restaurant> findByRestaurantOwnerEmail(String email);



    Optional<Restaurant> findRestaurantById(Integer id);

    List<Restaurant> findAllRestaurants();

    Restaurant saveRestaurant(Restaurant restaurant, User restaurantOwner, Address address);

    void deleteRestaurant(Integer restaurantId);


    List<Restaurant> findAllRestaurantByStreetName(String street);
}
