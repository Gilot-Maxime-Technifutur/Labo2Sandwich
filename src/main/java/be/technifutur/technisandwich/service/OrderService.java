package be.technifutur.technisandwich.service;

public interface OrderService {
    void create(String email);
    void cancel(long id);
    void done(long id);
}
