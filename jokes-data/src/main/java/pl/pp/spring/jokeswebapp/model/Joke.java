package pl.pp.spring.jokeswebapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Joke extends BaseEntity {

    private String title;

    @Lob
    private String content;

    @Transient //wyłącza pole z bazy danych
    private List<Category> categories = new ArrayList<>();

    @ManyToOne  //relacja wielu do jednego
    @JoinColumn(name = "user_id")   //zmiana nazwy kolumny
    private User user;

    public Joke() {
    }

    public Joke(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
