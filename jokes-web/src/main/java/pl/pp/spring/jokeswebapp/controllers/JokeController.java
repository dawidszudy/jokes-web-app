package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JokeController {

    private final CategoryService categoryService;
    private final JokeService jokeService;

    public JokeController(CategoryService categoryService, JokeService jokeService) {
        this.categoryService = categoryService;
        this.jokeService = jokeService;
    }

    //przekierowuje na formularz
    @GetMapping("/jokes/add")
    public String addJokeController(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        //przesłanie pustego new Joke na formularz
        model.addAttribute("joke", new Joke());
        return "jokes/add";
    }

    //zwracanie przekierowania na stronę główną
    //obsługuje wysłany formularz
    @PostMapping("/jokes/add")
    public String addJoke(@ModelAttribute Joke joke, @RequestParam("category") List<Long> categoryIds) {
        System.out.println(joke);

        List<Category> categories = new ArrayList<>();

        //wyszukanie kategorii dla zaznaczonych kategorii przez użytkownika
        for (Long id : categoryIds) {
            categories.add(categoryService.findById(id));
        }

        System.out.println(categories);

        //ustawienie kategorii dla dodawanego żartu
        joke.setCategories(categories);

        //dodanie do serwisu
        jokeService.save(joke);

        return "redirect:/";
    }
}
