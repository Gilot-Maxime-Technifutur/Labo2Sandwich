package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.RessourceNotFoundException;
import be.technifutur.technisandwich.model.entity.Cart;
import be.technifutur.technisandwich.model.entity.Sandwich;
import be.technifutur.technisandwich.model.entity.User;
import be.technifutur.technisandwich.repository.CartRepository;
import be.technifutur.technisandwich.repository.SandwichRepository;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final SandwichRepository sandwichRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            UserRepository userRepository,
            SandwichRepository sandwichRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.sandwichRepository = sandwichRepository;
    }

    @Override
    public Cart getCart(String email) {
        User user = userRepository.findByUserMail(email)
                .orElseThrow(RessourceNotFoundException::new);

        return cartRepository.findById(user.getCart().getId())
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public void modify(long id, long qt, String email) {
        Cart cart = getCart(email);
        Sandwich sandwich = sandwichRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        long i = cart.getSandwiches().stream()
                .filter(s -> s == sandwich)
                .count();
        if(i < qt)
            add(qt - i, sandwich, cart);
        if(i > qt)
            remove(i - qt, sandwich, cart);
    }

    @Override
    public void add(long id, long qt, String email) {
        Cart cart = getCart(email);
        Sandwich sandwich = sandwichRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);
        add(qt, sandwich, cart);
    }

    @Override
    public void remove(long id, long qt, String email) {
        Cart cart = getCart(email);
        Sandwich sandwich = sandwichRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);
        remove(qt, sandwich, cart);
    }

    private void add(long qt, Sandwich s, Cart c){
        for(int i = 0; i < qt; i++){
            c.getSandwiches().add(s);
        }
    }
    private void remove(long qt, Sandwich s, Cart c){
        for(int i = 0; i < qt; i++){
            c.getSandwiches().remove(s);
        }
    }
}
