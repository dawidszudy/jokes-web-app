package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pp.spring.jokeswebapp.services.JokeService;

@Controller //adnotacja (zamiast @Component)
public class IndexController {

    private final JokeService jokeService;

    public IndexController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @RequestMapping({"", "/", "/index"})    //jakie requesty przypisane
    public String showIndex(Model model) {      //metoda odwołująca się do pliku html
        //Joke joke = jokeService.findAll().get(0);
        //ustawienie atrybutu
        model.addAttribute("jokes", jokeService.findAll());
        return "index";              //nazwa bez .html
    }
}
