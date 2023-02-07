package be.technifutur.technisandwich.service;

import be.technifutur.technisandwich.model.entity.Order;

import java.util.Set;

public interface OrderService {
    void create(String email);
    void cancel(long id);
    void done(long id);
    void set(long id, String state);
    Set<Order> getAll(String email);
    Order getOne(long id, String email);
    Set<Order> getAll();
}
