package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.StreetDTO;
import pl.example.domain.Street;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StreetMapper {

    StreetDTO map(Street street);

    Street mapFromDTO(StreetDTO dto);
}
