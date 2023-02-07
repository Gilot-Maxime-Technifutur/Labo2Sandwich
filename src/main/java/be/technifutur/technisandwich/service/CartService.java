package be.technifutur.technisandwich.service;

import be.technifutur.technisandwich.model.entity.Cart;

public interface CartService {
    Cart getCart(String email);
}
