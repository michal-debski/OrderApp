package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Meal;
import pl.example.infrastructure.database.entity.MealEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealEntityMapper {



    MealEntity mapToEntity(Meal meal);


    Meal mapFromEntity(MealEntity entity);


}
