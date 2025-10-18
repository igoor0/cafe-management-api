package pl.obrona.managementapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.ApiException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User add(CreateUserCommand command) {
        if (userRepository.existsByUsername(command.getUsername())) {
            throw new ApiException("User already exists.");
        }

        User user = User.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .enabled(true)
                .role(command.getRole())
                .build();

        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
