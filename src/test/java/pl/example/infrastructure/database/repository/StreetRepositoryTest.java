package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.example.domain.Street;
import pl.example.infrastructure.database.entity.StreetEntity;
import pl.example.infrastructure.database.repository.jpa.StreetJpaRepository;
import pl.example.infrastructure.database.repository.mapper.StreetEntityMapper;
import pl.example.util.EntityInput;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StreetRepositoryTest {

    @Mock
    private StreetJpaRepository streetJpaRepository;
    @Mock
    private StreetEntityMapper streetEntityMapper;

    @InjectMocks
    private StreetRepository streetRepository;
    private StreetEntity streetEntity;
    private Street street;

    @BeforeEach
    void setUp() {
        streetEntity = EntityInput.kindOfStreetEntity();
        street = Street.builder()
                .streetId(streetEntity.getStreetId())
                .name(streetEntity.getName())
                .build();
    }

    @Test
    void shouldFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<StreetEntity> streetEntityPage = new PageImpl<>(Collections.singletonList(streetEntity));
        when(streetJpaRepository.findAll(pageable)).thenReturn(streetEntityPage);
        when(streetEntityMapper.mapFromEntity(any(StreetEntity.class))).thenReturn(street);

        Page<Street> result = streetRepository.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Testowa", result.getContent().get(0).getName());
    }

    @Test
    void shouldFindById() {
        when(streetJpaRepository.findById(1)).thenReturn(Optional.of(streetEntity));
        when(streetEntityMapper.mapFromEntity(streetEntity)).thenReturn(street);

        Street result = streetRepository.findById(1).orElseThrow();

        assertNotNull(result);
        assertEquals(1, result.getStreetId());
        assertEquals("Testowa", result.getName());

    }

}