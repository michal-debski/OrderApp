package pl.example.business.dao;

import pl.example.domain.Meal;
import pl.example.domain.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MealDAO {

    Set<Meal> findMealsByCategory(Integer categoryId);

    List<Meal> findAllMeals();

    Meal saveMeal(Meal meal, Restaurant restaurant);

    void deleteById(Integer id);

    List<Meal> findAllMealsBySelectedRestaurant(Integer id);

    Optional<Meal> findMealById(Integer mealId);

    void updateMeal(Meal meal, String name, String description, BigDecimal price, Restaurant restaurant);

}
