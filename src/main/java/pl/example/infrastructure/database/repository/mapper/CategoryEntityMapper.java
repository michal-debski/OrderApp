package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Category;
import pl.example.infrastructure.database.entity.CategoryEntity;

@Mapper(componentModel =  "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {


    @Mapping(target = "meal", ignore = true)
    Category mapFromEntity(CategoryEntity entity);
    @Mapping(target = "meal", ignore = true)
    CategoryEntity mapToEntity(Category category);
}
