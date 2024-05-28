package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.domain.Address;
import pl.example.infrastructure.database.entity.AddressEntity;
import pl.example.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.example.infrastructure.database.repository.mapper.AddressEntityMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressRepositoryTest {
    @Mock
    private AddressEntityMapper addressEntityMapper;

    @Mock
    private AddressJpaRepository addressJpaRepository;

    @InjectMocks
    private AddressRepository addressRepository;

    private Address address;
    private AddressEntity addressEntity;
    @BeforeEach
    public void setUp() {
        address = Address.builder().city("SampleCity").build();

        addressEntity = new AddressEntity();
        addressEntity.setCity("SampleCity");
    }
    @Test
    void shouldSaveAddress() {

            when(addressEntityMapper.mapToEntity(any(Address.class))).thenReturn(addressEntity);
            when(addressJpaRepository.saveAndFlush(any(AddressEntity.class))).thenReturn(addressEntity);
            when(addressEntityMapper.mapFromEntity(any(AddressEntity.class))).thenReturn(address);

            Address result = addressRepository.saveAddress(address);


            Mockito.verify(addressEntityMapper, times(1)).mapToEntity(address);
            Mockito.verify(addressJpaRepository, times(1)).saveAndFlush(addressEntity);
            Mockito.verify(addressEntityMapper, times(1)).mapFromEntity(addressEntity);

            assertEquals(address, result);
        }
    }
