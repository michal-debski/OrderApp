package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.RestaurantEntity;

import java.util.Optional;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity,Integer> {
    Optional<RestaurantEntity> findByName(String name);
//    List<RestaurantEntity> findByStreet(String street);


}
