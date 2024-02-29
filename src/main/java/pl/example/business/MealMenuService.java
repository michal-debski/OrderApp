package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.CategoryDAO;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Meal;
import pl.example.domain.exception.NotFoundException;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class MealMenuService {

    private final MealDAO mealDAO;

    private final CategoryDAO categoryDAO;
    private final CategoryService categoryService;

    @Transactional
    public Set<Meal> findMealByCategory(Integer categoryId) {
        Set<Meal> meal = mealDAO.findByCategory(categoryId);
        if (meal.isEmpty()) {
            throw new NotFoundException("Could not find meal by meal category id: [%s]".formatted(categoryId));
        }
        return meal;
    }


    public List<Meal> findAll() {
        List<Meal> meals = mealDAO.findAll();
        log.info("Available meals: [{}]", meals);
        return meals;
    }

    @Transactional
    public List<Meal> findAllBySelectedRestaurant(Integer id) {
        List<Meal> mealsForSelectedRestaurant = mealDAO.findAllBySelectedRestaurant(id);
//        if (mealsForSelectedRestaurant.size() == 0) {
//            throw new NotFoundException("Could not find meal for selected Restaurant [%s]".formatted(id));
//        }
        return mealsForSelectedRestaurant;
    }


    public void makeMealForRestaurant(Meal meal) {
           mealDAO.save(meal);
    }


    public void delete(Integer id) {
        mealDAO.deleteById(id);
    }



}
