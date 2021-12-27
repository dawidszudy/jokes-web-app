package pl.pp.spring.jokeswebapp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.services.CategoryService;

import java.util.List;

@RestController
public class CategoryRestController {

    private Logger log = LoggerFactory.getLogger(CategoryRestController.class);

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/api/categories"})
    public ResponseEntity<List<Category>> showCategoriesList() {
        log.info("showCategoriesList");

        //gdy status okej zwracamy zawartość listy
        return ResponseEntity.ok(categoryService.findAll());
    }

}