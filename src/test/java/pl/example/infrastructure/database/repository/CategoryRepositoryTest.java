package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.domain.Category;
import pl.example.infrastructure.database.entity.CategoryEntity;
import pl.example.infrastructure.database.repository.jpa.CategoryJpaRepository;
import pl.example.infrastructure.database.repository.mapper.CategoryEntityMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryTest {

    @Mock
    private CategoryJpaRepository categoryJpaRepository;
    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryRepository categoryRepository;
    private CategoryEntity categoryEntity;
    private CategoryEntity secondCategoryEntity;
    private Category category;
    private Category secondCategory;

    @BeforeEach
    public void setUp() {
        categoryEntity = CategoryEntity.builder().name("MAIN DISH").build();
        secondCategoryEntity = CategoryEntity.builder().name("DINNER").build();
        category = Category.builder().name("MAIN DISH").build();
        secondCategory = Category.builder().name("DINNER").build();
    }

    @Test
    void findAll() {
        when(categoryJpaRepository.findAll()).thenReturn(List.of(categoryEntity, secondCategoryEntity));
        when(categoryEntityMapper.mapFromEntity(categoryEntity)).thenReturn(category);
        when(categoryEntityMapper.mapFromEntity(secondCategoryEntity)).thenReturn(secondCategory);
        List<Category> categoryList = categoryRepository.findAll();

        Assertions.assertEquals(2, categoryList.size());
    }

    @Test
    void findById() {
        when(categoryJpaRepository.findById(1)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.mapFromEntity(categoryEntity)).thenReturn(category);
        Optional<Category> categoryById = categoryRepository.findById(1);
        Assertions.assertEquals(categoryById.get().getName(), "MAIN DISH");
    }
}