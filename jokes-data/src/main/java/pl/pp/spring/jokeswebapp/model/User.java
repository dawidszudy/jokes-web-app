package pl.pp.spring.jokeswebapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    //kaskadowe operacje na powiązanych (User i UserProfile)
    @OneToOne(cascade = CascadeType.ALL)
    private UserProfile userProfile;

    //relacja jeden do wielu
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Joke> jokes = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(Set<Joke> jokes) {
        this.jokes = jokes;
    }
}
