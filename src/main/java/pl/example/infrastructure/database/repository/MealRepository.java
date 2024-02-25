package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Category;
import pl.example.domain.Meal;
import pl.example.infrastructure.database.entity.CategoryEntity;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.CategoryJpaRepository;
import pl.example.infrastructure.database.repository.jpa.MealJpaRepository;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.MealEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class MealRepository implements MealDAO {


    private final MealJpaRepository mealJpaRepository;
    private final MealEntityMapper mealEntityMapper;
    private final CategoryJpaRepository categoryJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;


    @Override
    public List<Meal> findByCategory(Category category) {
        CategoryEntity categoryEntity = categoryJpaRepository.findByName(category.getName()).get();
        return mealJpaRepository.findByCategory(categoryEntity).map(mealEntityMapper::mapFromEntity).stream().toList();

    }


    @Override
    public List<Meal> findAll() {
        return mealJpaRepository.findAll().stream()
                .map(mealEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Meal save(Meal meal) {
        MealEntity mealEntity = mealEntityMapper.mapToEntity(meal);
        MealEntity saved = mealJpaRepository.saveAndFlush(mealEntity);
        return mealEntityMapper.mapFromEntity(saved);
    }


    @Override
    public void deleteById(Integer id) {
        mealJpaRepository.delete(mealJpaRepository.findById(id).get());
    }

    @Override
    public List<Meal> findAllBySelectedRestaurant(String name) {
        RestaurantEntity restaurantEntity = restaurantJpaRepository.findByName(name).get();
        return mealJpaRepository.findAllBySelectedRestaurant(name)
                .stream().map(mealEntityMapper::mapFromEntity).toList();
    }


}
