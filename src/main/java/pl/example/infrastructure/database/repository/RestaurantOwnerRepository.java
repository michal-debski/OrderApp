package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.RestaurantOwnerDAO;
import pl.example.domain.RestaurantOwner;
import pl.example.infrastructure.database.repository.jpa.RestaurantOwnerJpaRepository;
import pl.example.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;

@Repository
@AllArgsConstructor
public class RestaurantOwnerRepository implements RestaurantOwnerDAO {

    private final RestaurantOwnerJpaRepository restaurantOwnerJpaRepository;
    private final RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;
    @Override
    public RestaurantOwner findById(Integer id) {
        return restaurantOwnerJpaRepository.findById(id)
                .map(restaurantOwnerEntityMapper::mapFromEntity).get();
    }
}
