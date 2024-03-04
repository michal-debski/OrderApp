package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Meal;
import pl.example.infrastructure.database.entity.MealEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealEntityMapper {


    @Mapping(target = "category.meals", ignore = true)
    @Mapping(target = "orderItems.meals", ignore=true)

    MealEntity mapToEntity(Meal meal);
    @Mapping(target = "category.meals", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Meal mapFromEntity(MealEntity entity);


}
