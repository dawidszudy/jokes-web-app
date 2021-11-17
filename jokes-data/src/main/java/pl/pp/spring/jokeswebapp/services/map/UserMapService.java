package pl.pp.spring.jokeswebapp.services.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.services.UserService;


@Service
@Profile("map")
public class UserMapService extends BaseMapService<User> implements UserService {

    private Logger log = LoggerFactory.getLogger(UserMapService.class);

    @Override
    public User save(User user) {
        log.info("saving user map: {}", user.getUsername());
        return super.save(user);
    }
}
