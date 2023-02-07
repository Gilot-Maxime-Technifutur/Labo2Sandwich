package be.technifutur.technisandwich.service;

import be.technifutur.technisandwich.model.dto.SandwichDTO;
import be.technifutur.technisandwich.model.form.SandwichForm;

import java.util.List;
import java.util.Map;

public interface SandwichService {
    List<SandwichDTO> getAll();
    SandwichDTO getOne(long id);
    void addToCart(long id, long qt, String email);
    List<SandwichDTO> getAllWithDiet(String diet);
    void create(SandwichForm form);
    void update(long id, Map<String, String> mapValues);
    void delete(long id);
}
