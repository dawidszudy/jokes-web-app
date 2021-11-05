package pl.pp.spring.jokeswebapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;
import pl.pp.spring.jokeswebapp.services.UserProfileService;
import pl.pp.spring.jokeswebapp.services.UserService;


@Component
public class DataLoader implements CommandLineRunner {

    private final JokeService jokeService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final UserProfileService userProfileService;

    public DataLoader(JokeService jokeService, CategoryService categoryService, UserService userService, UserProfileService userProfileService) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @Override
    public void run(String... args) {

        User andrzejNowacki = new User();
        andrzejNowacki.setUsername("andrzejNowacki");
        andrzejNowacki.setEmail("andrzejNowacki@gmail.com");
        andrzejNowacki.setPassword("qwert");

        UserProfile andrzejNowackiProfile = new UserProfile();
        andrzejNowackiProfile.setFirstName("Andrzej");
        andrzejNowackiProfile.setLastName("Nowacki");

        userProfileService.save(andrzejNowackiProfile);

        andrzejNowacki.setUserProfile(andrzejNowackiProfile);

        User karolWozniak = new User();
        karolWozniak.setUsername("karolWozniak");
        karolWozniak.setEmail("karolWozniak@gmail.com");
        karolWozniak.setPassword("1234");

        userService.save(andrzejNowacki);
        userService.save(karolWozniak);


        Joke joke1 = getExampleJoke1();
        Joke joke2 = getExampleJoke2();

        Category category1 = new Category("Teściowa");
        Category category2 = new Category("Jaś");
        Category category3 = new Category("Szkoła");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);

        joke1.getCategories().add(category1);
        joke2.getCategories().add(category2);
        joke2.getCategories().add(category3);

        category1.getJokes().add(joke1);
        category2.getJokes().add(joke2);
        category3.getJokes().add(joke2);

        jokeService.save(joke1);
        jokeService.save(joke2);

        System.out.println("[DataLoader] data loaded");
    }

    private Joke getExampleJoke1() {
        Joke joke1 = new Joke();
        joke1.setTitle("Okup za teściową");
        joke1.setContent("Mężczyzna odbiera telefon:\n" +
                "-Słucham\n" +
                "*Mamy twoją teściową. musisz zapłacić 100 000 zł okupu - słyszy w telefonie.\n" +
                "-A co jeśli nie zapłacę? - Zastanawia się mężczyzna.\n" +
                "*To ją sklonujemy! - odpowiada porywacz.");
        return joke1;
    }

    private Joke getExampleJoke2() {
        Joke joke2 = new Joke();
        joke2.setTitle("Jaś i Partia");
        joke2.setContent("-Drogie dzieci, partia jest dla nas jak najlepszy przyjaciel.\n" +
                "*Proszę Pani! Partia jest dla nas jak brat.\n" +
                "-Brawo Jasiu. Brawo! Wyjaśnij wszystkim dzieciom dlaczego partia jest dla nas jak brat.\n" +
                "*A bo przyjaciela można wybrać, a brat jaki się pieron trafi z takim musisz wytrzymać.");
        return joke2;
    }

}
