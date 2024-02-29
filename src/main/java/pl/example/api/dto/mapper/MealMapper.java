package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.MealDTO;
import pl.example.domain.Meal;

@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface MealMapper {

    @Mapping(source="category", target = "category")
    MealDTO map(Meal meal);
    @Mapping(source="category", target = "category")

    Meal map(MealDTO meal);


}
