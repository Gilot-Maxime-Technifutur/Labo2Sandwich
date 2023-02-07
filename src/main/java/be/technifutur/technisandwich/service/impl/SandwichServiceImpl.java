package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.RessourceNotFoundException;
import be.technifutur.technisandwich.model.dto.SandwichDTO;
import be.technifutur.technisandwich.model.entity.Cart;
import be.technifutur.technisandwich.model.entity.Sandwich;
import be.technifutur.technisandwich.repository.CartRepository;
import be.technifutur.technisandwich.repository.SandwichRepository;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.SandwichService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SandwichServiceImpl implements SandwichService {
    private final SandwichRepository sandwichRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public SandwichServiceImpl(
            SandwichRepository sandwichRepository,
            UserRepository userRepository,
            CartRepository cartRepository) {
        this.sandwichRepository = sandwichRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<SandwichDTO> getAll() {
        return sandwichRepository.findAll().stream()
                .map(entity -> SandwichDTO.from(entity))
                .toList();
    }

    @Override
    public SandwichDTO getOne(long id) {
        return sandwichRepository.findById(id)
                .map(entity -> SandwichDTO.from(entity))
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public void addToCart(long id, String email) {
        Sandwich sandwich = sandwichRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        Cart userCart = userRepository.findByUserMail(email)
                .orElseThrow(RessourceNotFoundException::new)
                .getCart();

        userCart.getSandwiches().add(sandwich);
    }
}
