package pl.example.business.dao;

import pl.example.domain.Meal;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MealDAO {

    Set<Meal> findByCategory(Integer categoryId);

    List<Meal> findAll();

    void save(Meal meal);

    void deleteById(Integer id);

    List<Meal> findAllBySelectedRestaurant(Integer id);

    Optional<Meal> findById(Integer mealId);

}
