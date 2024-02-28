package pl.example.business.dao;

import pl.example.domain.Meal;

import java.util.List;
import java.util.Set;

public interface MealDAO {

    Set<Meal> findByCategory(Integer categoryId);

    List<Meal> findAll();

    Meal save(Meal meal);

    void deleteById(Integer id);

    List<Meal> findAllBySelectedRestaurant(Integer id);

}
