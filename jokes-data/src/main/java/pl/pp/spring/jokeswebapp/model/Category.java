package pl.pp.spring.jokeswebapp.model;

import java.util.List;

public class Category extends BaseEntity {

    private String name;
    private List<Joke> jokes;


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() +
                "name='" + name + '\'' +
                '}';
    }
}
