package be.technifutur.technisandwich.service;

import be.technifutur.technisandwich.model.entity.Cart;

public interface CartService {
    Cart getCart(String email);
    void modify(long id, long qt, String email);
    void add(long id, long qt, String email);
    void remove(long id, long qt, String email);
}
