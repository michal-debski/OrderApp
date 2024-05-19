package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor

public class MealMenuService {

    private final MealDAO mealDAO;

    public List<Meal> findAll() {
        List<Meal> meals = mealDAO.findAll();
        log.info("Available meals: [{}]", meals);
        return meals;
    }

    public List<Meal> findAllBySelectedRestaurant(Integer id) {
        List<Meal> mealsForSelectedRestaurant = mealDAO.findAllBySelectedRestaurant(id);

        return mealsForSelectedRestaurant;
    }

    @Transactional
    public Meal makeMealForRestaurant(Meal meal, Restaurant restaurant) {

        return mealDAO.save(meal, restaurant);
    }

    //@Transactional
    public void delete(Integer id) {
        mealDAO.deleteById(id);
    }

    public Optional<Meal> findMealById(Integer mealId) {
        return mealDAO.findById(mealId);
    }

    @Transactional
    public void update(Meal meal, String name, String description, BigDecimal price, Restaurant restaurant) {
        mealDAO.update(meal, name, description, price, restaurant);
    }
}
