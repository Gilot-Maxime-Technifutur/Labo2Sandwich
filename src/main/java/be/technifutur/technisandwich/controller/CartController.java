package be.technifutur.technisandwich.controller;

import be.technifutur.technisandwich.model.entity.Cart;
import be.technifutur.technisandwich.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public Cart getCart(Authentication authentication){
        return cartService.getCart((String) authentication.getPrincipal());
    }
}
