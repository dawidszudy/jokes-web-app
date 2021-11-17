package pl.pp.spring.jokeswebapp.services.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.repositories.CategoryRepository;
import pl.pp.spring.jokeswebapp.services.CategoryService;

import java.util.ArrayList;
import java.util.List;


@Service
@Profile("db")
public class CategoryDbService implements CategoryService {

    private Logger log = LoggerFactory.getLogger(CategoryDbService.class);

    private final CategoryRepository categoryRepository;

    public CategoryDbService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        log.info("find all Category");
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public Category findById(Long id) {
        log.info("find Category by id: {}", id);
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        log.info("saving category: {}",  category.getName());
        return categoryRepository.save(category);
    }
}