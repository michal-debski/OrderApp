package pl.example.infrastructure.database.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.util.EntityInput;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.example.util.EntityInput.kindOfRestaurantEntity;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MealJpaRepositoryTest {

    private MealJpaRepository mealJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Test
    void shouldSaveAndFindByCategory() {

        MealEntity meal1 = EntityInput.kindOfMealEntity1();
        MealEntity meal2 = EntityInput.kindOfMealEntity();
        RestaurantEntity restaurant = kindOfRestaurantEntity();
        restaurant.setRestaurantId(4);
        restaurantJpaRepository.save(restaurant);
        meal1.setRestaurant(restaurant);
        meal2.setRestaurant(restaurant);
        entityManager.merge(meal1);
        entityManager.merge(meal2);

        // when
        Set<MealEntity> foundMeals = mealJpaRepository.findByCategory(1);

        // then
        assertEquals(6, foundMeals.size());
    }

    @Test
    void shouldFindAllBySelectedRestaurant() {

        RestaurantEntity restaurantEntity = kindOfRestaurantEntity();

        List<MealEntity> expected = mealJpaRepository.findAllBySelectedRestaurant(restaurantEntity.getRestaurantId());
        assertEquals(0, expected.size());
    }
}