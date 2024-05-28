package pl.example.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.AddressDAO;
import pl.example.domain.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static pl.example.util.DomainInput.kindOfRestaurant;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressDAO addressDAO;

    @InjectMocks
    private AddressService addressService;

    @Test
    void saveAddress() {
        Address address = kindOfRestaurant().getAddress();

        when(addressService.saveAddress(address)).thenReturn(address);

        Address savedAddress = addressService.saveAddress(address);
        assertEquals(address, savedAddress);
        verify(addressDAO, times(1)).saveAddress(address);


    }
}