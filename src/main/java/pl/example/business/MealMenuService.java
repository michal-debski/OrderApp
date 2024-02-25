package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Category;
import pl.example.domain.Meal;
import pl.example.domain.exception.NotFoundException;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MealMenuService {

    private final MealDAO mealDAO;


    @Transactional
    public List<Meal> findMealByCategory(Category category) {
        List<Meal> meal = mealDAO.findByCategory(category);
        if (meal.isEmpty()) {
            throw new NotFoundException("Could not find meal by meal category: [%s]".formatted(category));
        }
        return meal;
    }


    public List<Meal> findAll() {
        List<Meal> meals = mealDAO.findAll();
        log.info("Available meals: [{}]", meals);
        return meals;
    }

    @Transactional
    public List<Meal> findAllBySelectedRestaurant(String name) {
        List<Meal> mealsForSelectedRestaurant = mealDAO.findAllBySelectedRestaurant(name);
        if (mealsForSelectedRestaurant.size() == 0) {
            throw new NotFoundException("Could not find meal for selected Restaurant [%s]".formatted(name));
        }
        return mealsForSelectedRestaurant;
    }

    @Transactional
    public Meal save(Meal meal) {
        return mealDAO.save(buildMeal(meal));
    }

    @Transactional
    public void delete(Integer id) {
        mealDAO.deleteById(id);
    }

    private Meal buildMeal(Meal meal) {
        return Meal.builder()
                .name(meal.getName())
                .description(meal.getDescription())
                .category(meal.getCategory())
                .price(meal.getPrice())
                .restaurant(meal.getRestaurant())
                .build();
    }

}
