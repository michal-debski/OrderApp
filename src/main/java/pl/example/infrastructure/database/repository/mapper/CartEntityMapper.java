package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Cart;
import pl.example.infrastructure.database.entity.CartEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartEntityMapper {

    CartEntity mapToEntity(Cart cart);

    Cart mapFromEntity(CartEntity saved);
}
