package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //adnotacja (zamiast @Component)
public class IndexController {

    @RequestMapping({"", "/", "/index"})    //jakie requesty przypisane
    public String showIndex() {      //metoda odwołująca się do pliku html
        return "index";              //nazwa bez .html
    }
}
