package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.infrastructure.database.entity.MealEntity;

public interface MealJpaRepository extends JpaRepository<MealEntity,Integer> {
}
