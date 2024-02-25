package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.CategoryDAO;
import pl.example.domain.Category;
import pl.example.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public List<Category> findAll() {
        return categoryDAO.findAll().stream().toList();
    }

    @Transactional
    public Category findCategory(String name) {
        Optional<Category> category = categoryDAO.findByName(name);
        if (category.isEmpty()) {
            throw new NotFoundException("Could not find category by name: [%s]".formatted(name));
        }
        return category.get();
    }

    @Transactional
    public Category saveCategory(Category category) {
        return categoryDAO.save(buildCategory(category));
    }

    private static Category buildCategory(Category category) {
        return Category.builder()
                .name(category.getName())
                .meal(category.getMeal())
                .build();
    }
}
