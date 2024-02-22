package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.infrastructure.database.entity.RestaurantEntity;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity,Integer> {
}
