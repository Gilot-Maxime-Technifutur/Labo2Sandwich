package be.technifutur.technisandwich.controller;

import be.technifutur.technisandwich.exception.FormValidationException;
import be.technifutur.technisandwich.model.entity.Cart;
import be.technifutur.technisandwich.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/modify")
    public String modify(@RequestParam long id, @RequestParam long qt, Authentication authentication){
        cartService.modify(id, qt, (String) authentication.getPrincipal());

        return "modify-succeed";
    }

    @PostMapping("/add")
    public String add(@RequestParam long id, @RequestParam long qt, Authentication authentication){
        cartService.add(id, qt, (String) authentication.getPrincipal());

        return "add-succeed";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam long id, @RequestParam long qt, Authentication authentication){
        cartService.remove(id, qt, (String) authentication.getPrincipal());

        return "remove-succeed";
    }
}
