package be.technifutur.technisandwich.service;

import be.technifutur.technisandwich.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getOne(long id);
}
