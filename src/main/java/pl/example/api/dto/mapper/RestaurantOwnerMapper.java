package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.RestaurantOwnerDTO;
import pl.example.domain.RestaurantOwner;
@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface RestaurantOwnerMapper {


     RestaurantOwnerDTO mapToDTO(RestaurantOwner restaurantOwner) ;
}
