package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.CategoryDTO;
import pl.example.domain.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryDTO map(Category category);

}
