package pl.example.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.example.infrastructure.database.entity.CategoryEntity;
import pl.example.infrastructure.database.entity.MealEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MealJpaRepository extends JpaRepository<MealEntity, Integer> {

    @Query("""
            SELECT meal FROM MealEntity meal 
            INNER JOIN FETCH meal.category category
            WHERE category.id = :categoryId
            """)
    Set<MealEntity> findByCategory(@Param("categoryId") Integer categoryId);

    @Query("""
            SELECT meal FROM MealEntity meal 
            INNER JOIN FETCH meal.restaurant rest
            WHERE rest.id = :restaurantId
            """)
    List<MealEntity> findAllBySelectedRestaurant(@Param("restaurantId") Integer restaurantId);
}
