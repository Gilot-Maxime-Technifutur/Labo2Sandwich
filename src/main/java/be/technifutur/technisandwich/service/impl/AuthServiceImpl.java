package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.FormValidationException;
import be.technifutur.technisandwich.jwt.JwtProvider;
import be.technifutur.technisandwich.jwt.JwtHolderDTO;
import be.technifutur.technisandwich.model.entity.User;
import be.technifutur.technisandwich.model.form.LoginForm;
import be.technifutur.technisandwich.model.form.RegistrationForm;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder encoder,
            AuthenticationManager authManager,
            JwtProvider jwtProvider
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void register(RegistrationForm form) {
        if( userRepository.existsByUserMail(form.getEmail()))
            throw new FormValidationException("email already taken");

        User user = form.toEntity();
        user.setPassword( encoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public JwtHolderDTO login(LoginForm form) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                form.getEmail(),
                form.getPassword()
        );

        auth = authManager.authenticate(auth);

        String token = jwtProvider.createToken(auth);

        return new JwtHolderDTO(token);
    }
}
