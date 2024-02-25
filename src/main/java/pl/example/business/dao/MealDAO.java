package pl.example.business.dao;

import pl.example.domain.Category;
import pl.example.domain.Meal;

import java.util.List;

public interface MealDAO {

    List<Meal> findByCategory(Category category);

    List<Meal> findAll();

    Meal save (Meal meal);

    void deleteById(Integer id);

    List<Meal> findAllBySelectedRestaurant(String name);

}
