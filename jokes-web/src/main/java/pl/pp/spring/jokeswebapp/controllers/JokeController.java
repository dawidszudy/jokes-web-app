package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class JokeController {

    private Logger log = LoggerFactory.getLogger(JokeController.class);

    private final CategoryService categoryService;
    private final JokeService jokeService;

    public JokeController(CategoryService categoryService, JokeService jokeService) {
        this.categoryService = categoryService;
        this.jokeService = jokeService;
    }

    //do wyszukiwania po kategoriach
    @RequestMapping({"/jokes"})
    public String showIndex(Model model, @RequestParam("categoryId") Long categoryId) {
        log.info("showJokesForCategory categoryId: {}", categoryId);
        model.addAttribute("jokes", categoryService.findById(categoryId).getJokes());
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    //przekierowuje na formularz
    @GetMapping("/jokes/add")
    public String addJokeForm(Model model) {
        log.info("addJokeForm");
        model.addAttribute("categories", categoryService.findAll());
        //przesłanie pustego new Joke na formularz
        model.addAttribute("joke", new Joke());
        return "jokes/add";
    }

    //zwracanie przekierowania na stronę główną
    //obsługuje wysłany formularz
    @PostMapping("/jokes/add")
    public String addJoke(@ModelAttribute Joke joke, @RequestParam("category") List<Long> categoryIds) {
        log.info("addJoke joke: {}, categories: {}", joke, categoryIds);
        //log.info("addJoke: {}", joke);

        Set<Category> categories = new HashSet<>();

        //wyszukanie kategorii dla zaznaczonych kategorii przez użytkownika
        for (Long id : categoryIds) {
            //categories.add(categoryService.findById(id));
            Category category = categoryService.findById(id);
            category.getJokes().add(joke);  //dodawanie joke-a
            categoryService.save(category);
            categories.add(category);   //dodawanie kategorii do tego joke-a
        }

        //System.out.println(categories);
        log.info("addJoke: {}", categories);

        //ustawienie kategorii dla dodawanego żartu
        joke.setCategories(categories);

        //dodanie do serwisu
        jokeService.save(joke);

        return "redirect:/";
    }
}
