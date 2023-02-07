package be.technifutur.technisandwich.service;

import be.technifutur.technisandwich.model.dto.SandwichDTO;

import java.util.List;

public interface SandwichService {
    List<SandwichDTO> getAll();
    SandwichDTO getOne(long id);
    void addToCart(long id, String email);
}
