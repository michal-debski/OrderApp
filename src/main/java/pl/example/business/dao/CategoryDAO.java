package pl.example.business.dao;

import pl.example.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDAO {

    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Integer Id);


}
