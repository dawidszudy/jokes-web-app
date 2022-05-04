package pl.pp.spring.jokeswebapp.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;
import pl.pp.spring.jokeswebapp.services.UserService;


@Component
public class DataLoader implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final CategoryService categoryService;
    //private final JokeService jokeService;
    private final UserService userService;

    public DataLoader(CategoryService categoryService, JokeService jokeService, UserService userService) {
        this.categoryService = categoryService;
        //this.jokeService = jokeService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        UserProfile andrzejNowackiProfile = new UserProfile();
        andrzejNowackiProfile.setFirstName("Andrew");
        andrzejNowackiProfile.setLastName("Nowacki");

        User andrzejNowacki = new User();
        andrzejNowacki.setUsername("andrewNowacki");
        andrzejNowacki.setEmail("andrewNowacki@gmail.com");
        andrzejNowacki.setPassword("qwert");

        andrzejNowacki.setUserProfile(andrzejNowackiProfile);

        User karolWozniak = new User();
        karolWozniak.setUsername("carolWozniak");
        karolWozniak.setEmail("carolWozniak@gmail.com");
        karolWozniak.setPassword("1234");


        Joke joke1 = getExampleJoke1();
        Joke joke2 = getExampleJoke2();

        Category category1 = new Category("Mother-in-law");
        Category category2 = new Category("Jonny");
        Category category3 = new Category("School");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);

        joke1.addCategory(category1);
        joke2.addCategory(category2);
        joke2.addCategory(category3);

        //unnecessary because addCategory method
//        category1.getJokes().add(joke1);
//        category2.getJokes().add(joke2);
//        category3.getJokes().add(joke2);

        //can cascade - which user to joke
        //unnecessary because setUser
//        andrzejNowacki.getJokes().add(joke1);
//        andrzejNowacki.getJokes().add(joke2);

        joke1.setUser(andrzejNowacki);
        joke2.setUser(andrzejNowacki);

        //niepotrzebne przy db bo jokes zapisywanie kaskadowo przez userService
        //potrzebne przy map
        //jokeService.save(joke1);
        //jokeService.save(joke2);

        //na końcu bo kaskadowo zapisuje jokes
        userService.save(andrzejNowacki);
        userService.save(karolWozniak);

        log.trace("trace"); //not displayed by default
        log.debug("debug"); //not displayed by default
        log.info("info");
        log.warn("warn");
        log.error("error");

        //System.out.println("[DataLoader] data loaded");
        log.info("data loaded");
    }

    private Joke getExampleJoke1() {
        Joke joke1 = new Joke();
        joke1.setTitle("Ransom for mother-in-law");
        joke1.setContent("A man take the phone:\n" +
                "-Please\n" +
                "*We have your mother-in-law. you have to pay a ransom of PLN 100,000 - he hears on the phone.\n" +
                "-What if I don't pay? The man wonders.\n" +
                "*Then we'll clone her!.");
        return joke1;
    }

    private Joke getExampleJoke2() {
        Joke joke2 = new Joke();
        joke2.setTitle("Johnny and political party");
        joke2.setContent("-Dear children, the party is like a best friend to us.\n" +
                "*Ma'am! The Party is like a brother to us.\n" +
                "-Bravo, Johnny. Explain to all the children why the political party is like a brother to us.\n" +
                "*Because you can choose your friend, brother whoever happens to be, \n" +
                " you're gonna have to withstand with him.");
        return joke2;
    }

}
