package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Restaurant;
import pl.example.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor

public class RestaurantService {

    private final RestaurantDAO restaurantDAO;


    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = restaurantDAO.findAll();
        log.info("Available restaurants: [{}]", restaurants.size());
        return restaurants;
    }

    @Transactional
    public Restaurant findByName(String name) {
        Optional<Restaurant> restaurant = restaurantDAO.findByName(name);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by name: [%s]".formatted(name));
        }
        return restaurant.get();
    }
//    @Transactional
//    public List<Restaurant> findByStreet(String street){
//        List<Restaurant> restaurant = restaurantDAO.findByStreet(street);
//        if(restaurant.size() == 0){
//            throw new NotFoundException("Could not find restaurant by street: [%s]".formatted(street));
//        }
//        return restaurant;
//    }

    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantDAO.saveRestaurant(buildRestaurant(restaurant));
    }

    @Transactional
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantDAO.deleteRestaurant(buildRestaurant(restaurant));
    }

    private static Restaurant buildRestaurant(Restaurant restaurant) {
        return Restaurant.builder()
                .name(restaurant.getName())
                .streets(restaurant.getStreets())
                .restaurantOwner(restaurant.getRestaurantOwner())
                .build();
    }
}
