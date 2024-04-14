package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.RestaurantOwner;
import pl.example.infrastructure.database.entity.RestaurantOwnerEntity;
import pl.example.infrastructure.database.repository.jpa.RestaurantOwnerJpaRepository;
import pl.example.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;

@Repository
@Slf4j
@AllArgsConstructor
public class RestaurantOwnerRepository implements RestaurantOwnerDAO {

    private final RestaurantOwnerJpaRepository restaurantOwnerJpaRepository;
    private final RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;

    @Override
    public RestaurantOwner findById(Integer id) {
        return restaurantOwnerJpaRepository.findById(id)
                .map(restaurantOwnerEntityMapper::mapFromEntity).get();
    }

    @Override
    public RestaurantOwner saveRestaurantOwner(RestaurantOwner restaurantOwner) {
        RestaurantOwnerEntity toSave = restaurantOwnerEntityMapper.mapToEntity(restaurantOwner);
        RestaurantOwnerEntity saved = restaurantOwnerJpaRepository.save(toSave);
        log.info("Restaurant Owner was created successfully with email: " + saved.getEmail());
        return restaurantOwnerEntityMapper.mapFromEntity(saved);
    }


}
