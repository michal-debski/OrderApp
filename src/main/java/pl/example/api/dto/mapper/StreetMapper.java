package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.example.api.dto.StreetDTO;
import pl.example.domain.Street;

@Mapper(componentModel = "spring")
public interface StreetMapper {
    StreetDTO map(Street street);
}
