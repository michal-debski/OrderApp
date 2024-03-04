package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.ClientDTO;
import pl.example.domain.Client;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientDTO mapToDTO(Client client);

    @Mapping(target = "client.orders", ignore = true)
    Client map(ClientDTO clientDTO);
}
