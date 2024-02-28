package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Category;
import pl.example.domain.Meal;
import pl.example.infrastructure.database.entity.CategoryEntity;
import pl.example.infrastructure.database.entity.MealEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealEntityMapper {

    @Mapping(target = "category.meals", ignore = true)
    @Mapping(target = "restaurant.meals", ignore = true)

    Meal mapFromEntity(MealEntity entity);
    @Mapping(target = "category.meals", ignore = true)
    @Mapping(target = "restaurant.meals", ignore = true)
    MealEntity mapToEntity(Meal meal);


}
