package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.RessourceNotFoundException;
import be.technifutur.technisandwich.model.entity.User;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getOne(long id) {
        return userRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);
    }
}
