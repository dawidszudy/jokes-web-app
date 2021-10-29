package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pp.spring.jokeswebapp.services.CategoryService;

@Controller
public class JokeController {

    private final CategoryService categoryService;

    public JokeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/jokes/add")
    public String addJokeController(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "jokes/add";
    }
}
