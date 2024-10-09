package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.CategoryDAO;
import pl.example.domain.Category;
import pl.example.api.controller.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public List<Category> findAllCategories() {
        return categoryDAO.findAll().stream().toList();
    }

    @Transactional
    public Category findCategoryById(Integer id) {
        Optional<Category> category = categoryDAO.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundException("Could not find category by id: [%s]".formatted(id));
        }
        return category.get();
    }

}
