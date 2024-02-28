package pl.example.business.dao;

import pl.example.domain.Street;

import java.util.List;
import java.util.Set;

public interface StreetDAO {

    List<Street> findAll();
    List<Street> findAllByRestaurantId(Integer id);
}
