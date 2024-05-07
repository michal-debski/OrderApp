package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.AddressDAO;
import pl.example.domain.Address;
import pl.example.infrastructure.database.entity.AddressEntity;
import pl.example.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.example.infrastructure.database.repository.mapper.AddressEntityMapper;

@Slf4j
@Repository
@AllArgsConstructor
public class AddressRepository implements AddressDAO {

    private final AddressEntityMapper addressEntityMapper;
    private final AddressJpaRepository addressJpaRepository;
    @Override
    public Address saveAddress(Address address) {
        AddressEntity addressEntity = addressEntityMapper.mapToEntity(address);
        AddressEntity entityToSave = addressJpaRepository.saveAndFlush(addressEntity);
        log.info("New address for city: [%s] has been created ".formatted(entityToSave.getCity()));
        return addressEntityMapper.mapFromEntity(entityToSave);
    }
}
