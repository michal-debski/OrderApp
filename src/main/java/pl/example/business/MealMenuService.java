package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Meal;

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

    @Transactional
    public List<Meal> findAllBySelectedRestaurant(Integer id) {
        List<Meal> mealsForSelectedRestaurant = mealDAO.findAllBySelectedRestaurant(id);

        return mealsForSelectedRestaurant;
    }

//    @Transactional
//    public Meal makeMealForRestaurant(Meal meal) {
//        Meal mealToAdd = Meal.builder()
//                .name(meal.getName())
//                .restaurant(meal.)
//                .price()
//                .description()
//                .build();
//        return mealDAO.save(mealToAdd);
//    }

    @Transactional
    public void delete(Integer id) {
        mealDAO.deleteById(id);
    }

    @Transactional
    public Optional<Meal> findMealById(Integer mealId) {
        return mealDAO.findById(mealId);
    }
}
