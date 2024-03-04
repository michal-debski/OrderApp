package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.OrderItemDTO;
import pl.example.domain.OrderItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapper {
    OrderItemDTO mapToDTO(OrderItem orderItem);

    OrderItem map(OrderItemDTO orderItemDTO);
}
