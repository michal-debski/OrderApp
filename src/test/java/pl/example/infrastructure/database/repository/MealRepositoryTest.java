package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.MealJpaRepository;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.MealEntityMapper;
import pl.example.util.DomainInput;
import pl.example.util.EntityInput;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealRepositoryTest {

    @Mock
    private MealJpaRepository mealJpaRepository;
    @Mock
    private MealEntityMapper mealEntityMapper;

    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;
    @InjectMocks
    private MealRepository mealRepository;


    private MealEntity mealEntity;
    private MealEntity mealEntity1;
    private RestaurantEntity restaurantEntity;
    private Restaurant restaurant;
    private Meal meal;
    private Meal meal1;

    @BeforeEach
    public void setUp() {
        mealEntity = EntityInput.kindOfMealEntity();
        mealEntity1 = EntityInput.kindOfMealEntity1();
        meal = DomainInput.kindOfMeal();
        meal1 = DomainInput.kindOfMeal1();
        restaurant = DomainInput.kindOfRestaurant();
        restaurantEntity = EntityInput.kindOfRestaurantEntity();

    }

    @Test
    void shouldFindByCategory() {
        when(mealJpaRepository.findByCategory(1)).thenReturn(Set.of(mealEntity, mealEntity1));
        when(mealEntityMapper.mapFromEntity(mealEntity)).thenReturn(meal);
        when(mealEntityMapper.mapFromEntity(mealEntity1)).thenReturn(meal1);

        Set<Meal> meals = mealRepository.findByCategory(1);
        Assertions.assertEquals(2, meals.size());

    }

    @Test
    void shouldFindAll() {
        when(mealJpaRepository.findAll()).thenReturn(List.of(mealEntity, mealEntity1));
        when(mealEntityMapper.mapFromEntity(mealEntity)).thenReturn(meal);
        when(mealEntityMapper.mapFromEntity(mealEntity1)).thenReturn(meal1);

        List<Meal> meals = mealRepository.findAll();
        Assertions.assertEquals(2, meals.size());

    }

    @Test
    void shouldSave() {
        when(restaurantJpaRepository.findByRestaurantName(restaurant.getRestaurantName()))
                .thenReturn(Optional.of(restaurantEntity));
        when(mealEntityMapper.mapToEntity(meal1)).thenReturn(mealEntity1);
        when(mealJpaRepository.save(mealEntity1)).thenReturn(mealEntity1);
        when(mealEntityMapper.mapFromEntity(mealEntity1)).thenReturn(meal1);

        Meal save = mealRepository.save(meal1, restaurant);
        Assertions.assertEquals(2, save.getMealId());
    }

    @Test
    void shouldDeleteById() {
        when(mealJpaRepository.findById(1)).thenReturn(Optional.of(mealEntity));
        mealRepository.deleteById(1);
        when(mealJpaRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertFalse(mealJpaRepository.findById(1).isPresent());
    }

    @Test
    void shouldFindAllBySelectedRestaurant() {
        when(mealJpaRepository.findAllBySelectedRestaurant(4)).thenReturn(List.of(mealEntity, mealEntity1));
        when(mealEntityMapper.mapFromEntity(mealEntity)).thenReturn(meal);
        when(mealEntityMapper.mapFromEntity(mealEntity1)).thenReturn(meal1);

        List<Meal> result = mealRepository.findAllBySelectedRestaurant(4);
        Assertions.assertEquals(2, result.size());

    }

    @Test
    void shouldFindById() {
        when(mealJpaRepository.findById(1)).thenReturn(Optional.of(mealEntity));
        when(mealEntityMapper.mapFromEntity(mealEntity)).thenReturn(meal);

        Optional<Meal> mealById = mealRepository.findById(1);

        org.assertj.core.api.Assertions.assertThat(mealById).isPresent();
        Assertions.assertEquals(meal, mealById.get());
    }

    @Test
    void shouldUpdate() {
        when(mealEntityMapper.mapToEntity(meal)).thenReturn(mealEntity);

        mealRepository.update(meal, "", "", new BigDecimal("1"), meal.getRestaurant());
        Assertions.assertEquals("", meal.getName());
    }
}