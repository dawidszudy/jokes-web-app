package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    private Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ResponseStatus(HttpStatus.NOT_FOUND) //wzięte klasy wyjątku
    @ExceptionHandler(NotFoundException.class) //który wyjątek określamy
    public ModelAndView handleNotFound(Exception exception) {
        //wypisanie loga z informacją o wyjątku - argument exception
        log.warn("Handle not found error: {}", exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        //ustawienie widoku po przechwyceniu - np. kierunek do strony html
        modelAndView.setViewName("errors/404");

        //dodanie obiektu z atrybutem "message" - po atrybucie wywołane w html
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) //wzięte klasy wyjątku
    @ExceptionHandler(NumberFormatException.class) //który wyjątek określamy
    public ModelAndView handleNumberFormatError(Exception exception) {
        log.warn("Handle not number format error: {}", exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("errors/400");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }
}
