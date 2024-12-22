package pl.example.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.api.controller.exception.CategoryNotFoundException;
import pl.example.business.dao.CategoryDAO;
import pl.example.domain.Category;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public List<Category> findAllCategories() {
        return categoryDAO.findAll().stream().toList();
    }

    @Transactional
    public Category findCategoryById(Integer id) {
        Optional<Category> category = categoryDAO.findById(id);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Could not find category by id: [%s]".formatted(id));
        }
        log.info("Successfully fetched category by id: " + category.get().getName());
        return category.get();
    }

}
