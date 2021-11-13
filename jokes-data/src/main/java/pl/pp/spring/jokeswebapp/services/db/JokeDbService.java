package pl.pp.spring.jokeswebapp.services.db;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.repositories.JokeRepository;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.util.List;


@Service
//@Primary   //adnotacja który serwis pierwszy
@Profile("db")
public class JokeDbService implements JokeService {

    private final JokeRepository jokeRepository;

    public JokeDbService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public List<Joke> findAll() {
        //List<Joke> jokes = new ArrayList<>();
        //jokeRepository.findAll().forEach(joke -> jokes.add(joke));
        //jokeRepository.findAll().forEach(jokes::add);
        return (List<Joke>) jokeRepository.findAll();
    }

    @Override
    public Joke findById(Long id) {
        return jokeRepository.findById(id).orElse(null);
    }

    @Override
    public Joke save(Joke joke) {
        System.out.println("joke db service");
        return jokeRepository.save(joke);
    }
}
