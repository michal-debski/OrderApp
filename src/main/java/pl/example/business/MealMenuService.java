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

    public List<Meal> findAllMeals() {
        List<Meal> meals = mealDAO.findAll();
        log.info("Available meals: [{}]", meals);
        return meals;
    }

    public List<Meal> findAllBySelectedRestaurant(Integer id) {
        List<Meal> selectedMealsByRestaurant = mealDAO.findAllBySelectedRestaurant(id);
        log.info("Available meals for restaurant: [{}]", selectedMealsByRestaurant);
        return selectedMealsByRestaurant;
    }

    @Transactional
    public Meal makeMealForRestaurant(Meal meal, Restaurant restaurant) {
        log.info("Making meal for restaurant: [{}]", restaurant);
        return mealDAO.save(meal, restaurant);
    }

    public void deleteMeal(Integer id) {
        log.info("Trying to delete meal with id: [{}]", id);
        mealDAO.deleteById(id);
    }

    public Optional<Meal> findMealById(Integer mealId) {
         log.info("Trying to find meal with id: [{}]", mealId);
        return mealDAO.findById(mealId);
    }

    @Transactional
    public void updateMeal(Meal meal, String name, String description, BigDecimal price, Restaurant restaurant) {
        log.info("Trying to update meal with name: [{}], description: [{}]", name, description);
        mealDAO.update(meal, name, description, price, restaurant);
    }
}
