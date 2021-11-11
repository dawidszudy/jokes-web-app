package pl.pp.spring.jokeswebapp.services.db;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.repositories.UserProfileRepository;
import pl.pp.spring.jokeswebapp.services.UserProfileService;


import java.util.List;


@Service
@Primary
public class UserProfileDbService implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileDbService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public List<UserProfile> findAll() {
        //List<UserProfile> userProfiles = new ArrayList<>();
        //userProfileRepository.findAll().forEach(user -> userProfiles.add(user));
        //userProfileRepository.findAll().forEach(userProfiles::add);
        return (List<UserProfile>) userProfileRepository.findAll();
    }

    @Override
    public UserProfile findById(Long id) {
        return userProfileRepository.findById(id).orElse(null);
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        System.out.println("user Profile db service");
        return userProfileRepository.save(userProfile);
    }
}
