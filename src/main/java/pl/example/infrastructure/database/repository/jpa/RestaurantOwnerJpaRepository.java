package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.RestaurantOwnerEntity;

import java.util.Optional;

@Repository
public interface RestaurantOwnerJpaRepository extends JpaRepository<RestaurantOwnerEntity, Integer> {
    Optional<RestaurantOwnerEntity> findById(Integer id);


}
