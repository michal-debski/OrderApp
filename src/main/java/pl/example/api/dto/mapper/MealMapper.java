package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.example.api.dto.CategoryDTO;
import pl.example.api.dto.MealDTO;
import pl.example.domain.Category;
import pl.example.domain.Meal;

@Mapper(componentModel = "spring")
public interface MealMapper {


    MealDTO map(Meal meal);

    Meal map(MealDTO meal);



}
