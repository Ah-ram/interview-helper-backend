package road_to_employment.interview_helper.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import road_to_employment.interview_helper.user.entity.Role;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "road_to_employment.interview_helper.user")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> maybeUser = userRepository.findById(id);

        return maybeUser.orElse(null);
    }

    @Override
    public User findByProviderId(String providerId) {
        Optional<User> maybeUser = userRepository.findByProviderId(providerId);

        return maybeUser.orElse(null);
    }

    @Override
    public User create(String provider, String providerId, String registerId) {
        User user = User.builder()
                .provider(provider)
                .providerId(providerId)
                .registerId(registerId)
                .role(Role.USER).build();
        return userRepository.save(user);
    }
}
