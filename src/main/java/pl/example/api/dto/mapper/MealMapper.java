package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.example.api.dto.MealDTO;
import pl.example.domain.Meal;

@Mapper(componentModel = "spring")
public interface MealMapper {
    MealDTO map(final Meal meal);
}
