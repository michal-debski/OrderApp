package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.MealDTO;
import pl.example.domain.Meal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealMapper {


    MealDTO map(Meal meal);


    Meal map(MealDTO meal);


}
