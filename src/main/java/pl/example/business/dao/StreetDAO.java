package pl.example.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.example.domain.Street;

import java.util.Optional;

public interface StreetDAO {

    Page<Street> findAll(Pageable pageable);
    Optional<Street> findById(Integer streetId);
}
