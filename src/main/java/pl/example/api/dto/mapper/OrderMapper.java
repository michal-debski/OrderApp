package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.OrderDTO;
import pl.example.domain.Order;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface OrderMapper {
    OrderDTO mapToDTO(Order client);


    Order map(OrderDTO clientDTO);
}
