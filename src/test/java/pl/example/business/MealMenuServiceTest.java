package pl.example.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.example.util.DomainInput.*;

@ExtendWith(MockitoExtension.class)
class MealMenuServiceTest {

    @Mock
    MealDAO mealDAO;
    @InjectMocks
    MealMenuService mealMenuService;

    @Test
    void findAll() {

        Meal meal = kindOfMeal();
        Meal secondMeal = kindOfMeal1();

        when(mealDAO.findAll()).thenReturn(List.of(meal, secondMeal));

        List<Meal> allMeals = mealMenuService.findAll();

        assertEquals(2, allMeals.size());
    }

    @Test
    void findAllBySelectedRestaurant() {
        Restaurant restaurant = kindOfRestaurant();
        Meal meal = kindOfMeal();
        Meal secondMeal = kindOfMeal1();

        when(mealDAO.findAllBySelectedRestaurant(restaurant.getRestaurantId())).thenReturn(List.of(meal, secondMeal));

        List<Meal> allMealsBySelectedRestaurant = mealMenuService
                .findAllBySelectedRestaurant(restaurant.getRestaurantId());

        assertEquals(2, allMealsBySelectedRestaurant.size());

    }

    @Test
    void makeMealForRestaurant() {
        Restaurant restaurant = kindOfRestaurant();
        Meal meal = kindOfMeal();

        when(mealDAO.save(meal, restaurant)).thenReturn(meal);

        Meal createdMeal = mealMenuService.makeMealForRestaurant(meal, restaurant);

        assertEquals(meal, createdMeal);
    }

    @Test
    void delete() {
        Integer mealId = 1;

        mealMenuService.delete(mealId);


        verify(mealDAO).deleteById(mealId);
    }

    @Test
    void findMealById() {
        Meal meal = kindOfMeal().withMealId(10);

        when(mealDAO.findById(10)).thenReturn(Optional.of(meal));

        Optional<Meal> result = mealMenuService.findMealById(10);

        Assertions.assertThat(result).isPresent();
        assertEquals(meal, result.get());
    }

    @Test
    void update() {
        Meal meal = kindOfMeal();
        meal.setDescription("testing meal");
        meal.setPrice(new BigDecimal("11"));
        meal.setName("testing meal");
        Restaurant restaurant = kindOfRestaurant();
        // When
        mealMenuService.update(meal, meal.getName(), meal.getDescription(), meal.getPrice(), restaurant);

        // Then
        verify(mealDAO).update(meal, meal.getName(), meal.getDescription(), meal.getPrice(), restaurant);
    }
}