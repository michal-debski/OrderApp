package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Category;
import pl.example.infrastructure.database.entity.CategoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {


    Category mapFromEntity(CategoryEntity entity);


    CategoryEntity mapToEntity(Category category);
}
