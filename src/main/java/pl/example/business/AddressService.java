package pl.example.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.AddressDAO;
import pl.example.domain.Address;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressDAO addressDAO;
    @Transactional
    public Address saveAddress(Address address) {
        log.info("Saving address {}", address);
        return addressDAO.saveAddress(address);
    }
}
