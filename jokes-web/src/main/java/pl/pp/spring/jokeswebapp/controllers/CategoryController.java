package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.services.CategoryService;

@Controller
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/categories"})
    public String showCategoriesList(Model model) {
        log.info("showCategoriesList");
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    //przekierowuje na formularz
    @GetMapping("/categories/add")
    public String showAddCategoryForm(Model model) {
        log.info("showCategoryForm");

        model.addAttribute("category", new Category());

        return "categories/save";
    }

    @GetMapping("/categories/{categoryId}/edit") //wprowadzenie zmiennej
    public String showEditCategoryForm(@PathVariable Long categoryId, Model model) {
        log.info("showCategoryForm");

        //pobranie tego id kategorii
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);

        return "categories/save";
    }


    @PostMapping("/categories/save")
    public String processCategoryForm(@ModelAttribute Category category) {
        log.info("processCategoryForm");

        //zapisanie kategorii za pomocą serwisu
        categoryService.save(category);

        return "redirect:/categories";
    }

    @GetMapping("/categories/{categoryId}/delete")
    public String deleteCategory(@PathVariable Long categoryId) {
        log.info("deleteCategory");

        categoryService.deleteById(categoryId);

        return "redirect:/categories";
    }
}
