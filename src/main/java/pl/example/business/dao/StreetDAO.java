package pl.example.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.example.domain.Street;

public interface StreetDAO {

    Page<Street> findAll(Pageable pageable);
    Street findById(Integer streetId);
}
