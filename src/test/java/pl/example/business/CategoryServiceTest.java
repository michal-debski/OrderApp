package pl.example.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.business.dao.CategoryDAO;
import pl.example.domain.Category;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static pl.example.util.DomainInput.kindOfCategory;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryDAO categoryDAO;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void findAll() {
        Category mainDish = kindOfCategory().withName("MAIN DISH");
        Category dessert = kindOfCategory().withName("DESSERT");
        Category soup = kindOfCategory().withName("SOUP");
        Category drink = kindOfCategory().withName("DRINK");

        when(categoryDAO.findAll()).thenReturn(List.of(mainDish, dessert, soup, drink));

        List<Category> allCategories = categoryService.findAll();

        assertEquals(4, allCategories.size());
    }

    @Test
    void findById() {
        Category category = kindOfCategory().withCategoryId(1);

        when(categoryDAO.findById(1)).thenReturn(Optional.of(category));

        Category result = categoryService.findById(category.getCategoryId());

        assertEquals(category, result);
    }
}