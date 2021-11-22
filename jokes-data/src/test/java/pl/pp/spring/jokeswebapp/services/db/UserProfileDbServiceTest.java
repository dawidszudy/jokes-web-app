package pl.pp.spring.jokeswebapp.services.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.repositories.UserProfileRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfileDbServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UserProfileDbService userProfileDbService;

    private final UserProfile userProfile = new UserProfile();

    @Test
    void findAllShouldReturnEmptyList() {
        when(userProfileRepository.findAll()).thenReturn(new HashSet<>());

        List<UserProfile> userProfiles = userProfileDbService.findAll();

        assertEquals(0, userProfiles.size());
    }

    @Test
    void findAllShouldReturnListWithOneElement() {
        when(userProfileRepository.findAll()).thenReturn(Set.of(userProfile));

        List<UserProfile> userProfiles = userProfileDbService.findAll();

        assertEquals(1, userProfiles.size());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }
}