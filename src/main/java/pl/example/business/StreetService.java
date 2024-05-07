package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.example.business.dao.StreetDAO;
import pl.example.domain.Street;

@Slf4j
@Service
@AllArgsConstructor
public class StreetService {
    private StreetDAO streetDAO;

    public Page<Street> findAll(Pageable pageable) {
        return streetDAO.findAll(pageable);
    }

    public Street findById(Integer id) {
        return streetDAO.findById(id);
    }
}
