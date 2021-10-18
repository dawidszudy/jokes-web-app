package pl.pp.spring.jokeswebapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.JokeService;

//interfejs pozwala na wykonanie metody przy starcie aplikacji
@Component
public class DataLoader implements CommandLineRunner {
    //pole z serwisem
    private final JokeService jokeService;
    //wstrzyknięcie poprzez konstruktor bo final
    public DataLoader(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @Override
    public void run(String... args) {

        Joke joke1 = new Joke();
        joke1.setTitle("Okup za teściową");
        joke1.setContent("Mężczyzna odbiera telefon:\n" +
                "-Słucham\n" +
                "*Mamy twoją teściową. musisz zapłacić 100 000 zł okupu - słyszy w telefonie.\n" +
                "-A co jeśli nie zapłacę? - Zastanawia się mężczyzna.\n" +
                "*To ją sklonujemy! - odpowiada porywacz.");

        Joke joke2 = new Joke();
        joke2.setTitle("Jaś i Partia");
        joke2.setContent("-Drogie dzieci, partia jest dla nas jak najlepszy przyjaciel.\n" +
                "*Proszę Pani! Partia jest dla nas jak brat.\n" +
                "-Brawo Jasiu. Brawo! Wyjaśnij wszystkim dzieciom dlaczego partia jest dla nas jak brat.\n" +
                "*A bo przyjaciela można wybrać, a brat jaki się pieron trafi z takim musisz wytrzymać.");

        jokeService.save(joke1);
        jokeService.save(joke2);

        System.out.println("[DataLoader] data loaded");
    }

}
