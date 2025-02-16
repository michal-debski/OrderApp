package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.CategoryDAO;
import pl.example.domain.Category;
import pl.example.infrastructure.database.repository.jpa.CategoryJpaRepository;
import pl.example.infrastructure.database.repository.mapper.CategoryEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepository implements CategoryDAO {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    @Override
    public List<Category> findAllCategories() {
        return categoryJpaRepository.findAll().stream()
                .map(categoryEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoryJpaRepository.findById(id)
                .map(categoryEntityMapper::mapFromEntity);
    }


}
