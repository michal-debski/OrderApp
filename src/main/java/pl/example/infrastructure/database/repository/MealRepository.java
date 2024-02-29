package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Meal;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.CategoryJpaRepository;
import pl.example.infrastructure.database.repository.jpa.MealJpaRepository;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.MealEntityMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class MealRepository implements MealDAO {


    private final MealJpaRepository mealJpaRepository;
    private final MealEntityMapper mealEntityMapper;
    private final CategoryJpaRepository categoryJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;


    @Override
    public Set<Meal> findByCategory(Integer categoryId) {
        return mealJpaRepository.findByCategory(categoryId).stream()
                .map(mealEntityMapper::mapFromEntity).collect(Collectors.toSet());
    }


    @Override
    public List<Meal> findAll() {
        return mealJpaRepository.findAll().stream()
                .map(mealEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void save(Meal meal) {
        MealEntity mealEntity = mealEntityMapper.mapToEntity(meal);

        mealJpaRepository.save(mealEntity);

    }


    @Override
    public void deleteById(Integer id) {
        mealJpaRepository.delete(mealJpaRepository.findById(id).get());
    }

    @Override
    public List<Meal> findAllBySelectedRestaurant(Integer id) {
        RestaurantEntity restaurantEntity = restaurantJpaRepository.findById(id).get();
        return mealJpaRepository.findAllBySelectedRestaurant(id)
                .stream().map(mealEntityMapper::mapFromEntity).toList();
    }


}
