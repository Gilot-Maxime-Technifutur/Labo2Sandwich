package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.RessourceNotFoundException;
import be.technifutur.technisandwich.model.entity.Cart;
import be.technifutur.technisandwich.model.entity.User;
import be.technifutur.technisandwich.repository.CartRepository;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart getCart(String email) {
        User user = userRepository.findByUserMail(email)
                .orElseThrow(RessourceNotFoundException::new);

        return cartRepository.findById(user.getCart().getId())
                .orElseThrow(RessourceNotFoundException::new);
    }
}
