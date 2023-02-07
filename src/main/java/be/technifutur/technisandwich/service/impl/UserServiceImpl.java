package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.RessourceNotFoundException;
import be.technifutur.technisandwich.model.entity.Cart;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
