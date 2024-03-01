package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Restaurant;
import pl.example.domain.exception.NotFoundException;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.RestaurantEntityMapper;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor

public class RestaurantService {

    private final RestaurantDAO restaurantDAO;

    private final RestaurantEntityMapper restaurantEntityMapper;


    private final RestaurantJpaRepository restaurantJpaRepository;
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = restaurantDAO.findAll();
        log.info("Available restaurants: [{}]", restaurants.size());
        return restaurants;
    }

    @Transactional
    public Restaurant findRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantDAO.findById(id);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by name: [%s]".formatted(id));
        }
        return restaurant.get();
    }

    @Transactional
    public Restaurant findByName(String name) {
        Optional<Restaurant> restaurant = restaurantDAO.findByName(name);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by name: [%s]".formatted(name));
        }
        return restaurant.get();
    }


    public void saveRestaurant(Restaurant restaurant) {
       restaurantDAO.saveRestaurant(restaurant);
    }


//    public void deleteRestaurant(Restaurant restaurant) {
//        restaurantDAO.deleteRestaurant(buildRestaurant(restaurant));
//    }

    @Transactional
    public List<Restaurant> findByRestaurantOwnerId(Integer id) {
        return restaurantDAO.findByRestaurantOwnerId(id);
    }
    @Transactional
    public List<Restaurant> findAllByStreetName(String name){
        return restaurantDAO.findAllByStreet(name);
    }

}

