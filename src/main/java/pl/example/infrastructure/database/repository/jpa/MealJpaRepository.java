package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.CategoryEntity;
import pl.example.infrastructure.database.entity.MealEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealJpaRepository extends JpaRepository<MealEntity, Integer> {

    Optional<MealEntity> findByCategory(CategoryEntity category);

    @Query("""
            SELECT meal FROM MealEntity meal 
            WHERE meal.restaurant.name = :restaurantName
            """)
    List<MealEntity> findAllBySelectedRestaurant(@Param("restaurantName") String restaurantName);
}
