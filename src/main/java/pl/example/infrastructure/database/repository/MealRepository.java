package pl.example.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.MealDAO;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.MealJpaRepository;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.MealEntityMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class MealRepository implements MealDAO {


    private final MealJpaRepository mealJpaRepository;
    private final MealEntityMapper mealEntityMapper;
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
    public Meal save(Meal meal, Restaurant restaurant) {
        MealEntity mealEntity = mealEntityMapper.mapToEntity(meal);
        RestaurantEntity restaurantEntity = getRestaurantEntityByName(restaurant);
        mealEntity.setRestaurant(restaurantEntity);
        MealEntity entitySaved = mealJpaRepository.save(mealEntity);
        return mealEntityMapper.mapFromEntity(entitySaved);
    }


    @Override
    public void deleteById(Integer id) {
        mealJpaRepository.delete(mealJpaRepository.findById(id).get());
    }

    @Override
    public List<Meal> findAllBySelectedRestaurant(Integer id) {
        return mealJpaRepository.findAllBySelectedRestaurant(id)
                .stream().map(mealEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<Meal> findById(Integer mealId) {
        return mealJpaRepository.findById(mealId)
                .map(mealEntityMapper::mapFromEntity);
    }

    @Override
    public void update(Meal meal, String name, String description, BigDecimal price, Restaurant restaurant) {
        meal.setName(name);
        meal.setDescription(description);
        meal.setPrice(price);
        meal.setRestaurant(restaurant);
        mealJpaRepository.save(mealEntityMapper.mapToEntity(meal));
    }

    private RestaurantEntity getRestaurantEntityByName(Restaurant restaurant) {
        return restaurantJpaRepository.findByRestaurantName(restaurant.getRestaurantName())
                .orElseThrow(() -> new EntityNotFoundException("Could not find restaurant with name: [%s]"
                        .formatted(restaurant.getRestaurantName())));
    }


}
