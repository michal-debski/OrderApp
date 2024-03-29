package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantDAO;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;
import pl.example.infrastructure.database.repository.mapper.RestaurantEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public List<Restaurant> findByRestaurantOwnerId(Integer id) {
        return restaurantJpaRepository.findByRestaurantOwnerId(id).stream()
                .map(restaurantEntityMapper::mapFromEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        return restaurantJpaRepository.findByName(name)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Restaurant> findById(Integer id) {
        return restaurantJpaRepository.findById(id)
                .map(restaurantEntityMapper::mapFromEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantJpaRepository.findAll().stream()
                .map(restaurantEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.mapToEntity(restaurant);
        restaurantJpaRepository.save(restaurantEntity);
    }

    @Override
    public void deleteRestaurant(Integer restaurantId) {
        restaurantJpaRepository.delete(restaurantJpaRepository.getById(restaurantId));
    }

    @Override
    public List<Restaurant> findAllByStreet(String name) {
        return restaurantJpaRepository.findAllByStreet(name).stream()
                .map(restaurantEntityMapper::mapFromEntity).toList();
    }


}
