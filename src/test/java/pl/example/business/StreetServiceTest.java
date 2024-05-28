package pl.example.business;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.example.business.dao.StreetDAO;
import pl.example.domain.Street;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pl.example.util.DomainInput.kindOfStreet;
import static pl.example.util.DomainInput.kindOfStreet1;

@ExtendWith(MockitoExtension.class)
class StreetServiceTest {

    @Mock
    private StreetDAO streetDAO;

    @InjectMocks
    private StreetService streetService;

    @Test
    void shouldFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Street> streetList = List.of(kindOfStreet(), kindOfStreet1());
        Page<Street> streetsPage = new PageImpl<>(streetList, pageable, streetList.size());
        when(streetDAO.findAll(pageable)).thenReturn(streetsPage);

        Page<Street> result = streetService.findAll(pageable);
        Assertions.assertThat(result).isNotEmpty();
        org.junit.jupiter.api.Assertions.assertEquals(2, result.getNumberOfElements());
    }

    @Test
    void shouldFindById() {
        Street street = kindOfStreet().withStreetId(1);
        when(streetDAO.findById(1)).thenReturn(Optional.of(street));

        Street result = streetService.findById(1);

        assertEquals(street, result);

    }

    @Test
    void shouldThrowExceptionWhenNotFindById() {
        Integer id = 1;
        when(streetDAO.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> streetService.findById(id));
        Assertions.assertThat(exception.getMessage())
                .isEqualTo("Street with id: [%s] is not present in database".formatted(id));
    }
}