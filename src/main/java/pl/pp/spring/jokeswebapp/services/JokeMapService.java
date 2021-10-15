package pl.pp.spring.jokeswebapp.services;

import pl.pp.spring.jokeswebapp.model.Joke;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JokeMapService implements JokeService {

    public Map<Long, Joke> jokeMap;

    //nadpisane metody z zaimplementowanego serwisu

    //metoda podająca wszystkie wartości z mapy
    @Override
    public List<Joke> findAll() {
        return new ArrayList<>(jokeMap.values());
    }

    //metoda zamieniająca lub zapisująca parę klucz - wartość
    @Override
    public Joke save(Joke joke) {
        if (joke.getId() == null) { //save
            Long maxId = jokeMap.keySet().stream().max(Long::compare).orElse(1L);
            joke.setId(maxId + 1);
        }
        jokeMap.put(joke.getId(), joke);    //update
        return joke;
    }
}
