package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantStreetDAO;
import pl.example.domain.RestaurantStreet;
import pl.example.infrastructure.database.repository.jpa.RestaurantStreetJpaRepository;
import pl.example.infrastructure.database.repository.jpa.StreetJpaRepository;
import pl.example.infrastructure.database.repository.mapper.RestaurantStreetEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RestaurantStreetRepository implements RestaurantStreetDAO {

    private final StreetJpaRepository streetJpaRepository;
    private final RestaurantStreetJpaRepository restaurantStreetJpaRepository;
    private final RestaurantStreetEntityMapper restaurantStreetEntityMapper;


    @Override
    public List<RestaurantStreet> findAll() {
        return restaurantStreetJpaRepository.findAll().stream()
                .map(restaurantStreetEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantStreet> findAllByRestaurantId(Integer id) {
        return restaurantStreetJpaRepository.findAllByRestaurantId(id).stream()
                .map(restaurantStreetEntityMapper::mapFromEntity).collect(Collectors.toList());
    }

    @Override
    public void save(RestaurantStreet restaurantStreet) {

        restaurantStreetJpaRepository.save(restaurantStreetEntityMapper.mapToEntity(restaurantStreet));


    }

    @Override
    public void deleteById(Integer streetId) {
        restaurantStreetJpaRepository.delete(restaurantStreetJpaRepository.findById(streetId).get());
    }
}
