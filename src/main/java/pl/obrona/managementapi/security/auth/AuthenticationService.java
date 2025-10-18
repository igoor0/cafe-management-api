package pl.obrona.managementapi.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.ApiException;
import pl.obrona.managementapi.common.exception.NotFoundException;
import pl.obrona.managementapi.security.JwtService;
import pl.obrona.managementapi.user.User;
import pl.obrona.managementapi.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        User user = userRepository.findByUsername(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + request.getEmail()));
        var jwtToken = jwtService.generateToken(
                user,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().toString()
        );
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        var user = userRepository.findByUsername(changePasswordRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + changePasswordRequest.getEmail()));
        if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(
                    user,
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole().toString());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new ApiException("Old password is incorrect");
        }
    }
}
