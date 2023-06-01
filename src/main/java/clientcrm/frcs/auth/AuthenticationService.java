package clientcrm.frcs.auth;

import clientcrm.frcs.config.JwtService;
import clientcrm.frcs.entities.Role;
import clientcrm.frcs.entities.User;
import clientcrm.frcs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse registerUser(RegisterRequest request) {
        Optional<User> userOptional = repository.findUserByEmail(request.getEmail());

        if(userOptional.isPresent())
            return AuthenticationResponse.builder().build();

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(new Role(2,"USER"))
                .phone(request.getPhone())
                .build();
        var savedUser = repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getIdUser());
        claims.put("fullName", savedUser.getFirstName() + " " + savedUser.getLastName());

        String token = UUID.randomUUID().toString();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {

        Optional<User> userOptional = repository.findUserByEmail(request.getEmail());

        if(userOptional.isPresent())
            return AuthenticationResponse.builder().build();

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(new Role(1,"ADMIN"))
                .phone(request.getPhone())
                .build();
        var savedUser = repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getIdUser());
        claims.put("fullName", savedUser.getFirstName() + " " + savedUser.getLastName());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );

        var user = repository.findUserByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
