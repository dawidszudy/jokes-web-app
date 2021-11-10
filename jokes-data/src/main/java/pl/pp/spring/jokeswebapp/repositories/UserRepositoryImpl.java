package pl.pp.spring.jokeswebapp.repositories;

import org.springframework.stereotype.Repository;
import pl.pp.spring.jokeswebapp.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public User save(User user) {
        System.out.println("simulation of inserting do user table");
        return null;
    }
}
