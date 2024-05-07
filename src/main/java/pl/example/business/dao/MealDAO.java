package pl.example.business.dao;

import pl.example.domain.Meal;
import pl.example.domain.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MealDAO {

    Set<Meal> findByCategory(Integer categoryId);

    List<Meal> findAll();

    Meal save(Meal meal, Restaurant restaurant);

    void deleteById(Integer id);

    List<Meal> findAllBySelectedRestaurant(Integer id);

    Optional<Meal> findById(Integer mealId);

    void update(Meal meal, String name, String description, BigDecimal price, Restaurant restaurant);

}
