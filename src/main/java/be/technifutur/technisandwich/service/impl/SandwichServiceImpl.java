package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.RessourceNotFoundException;
import be.technifutur.technisandwich.model.dto.SandwichDTO;
import be.technifutur.technisandwich.model.entity.Cart;
import be.technifutur.technisandwich.model.entity.Diet;
import be.technifutur.technisandwich.model.entity.Sandwich;
import be.technifutur.technisandwich.model.form.SandwichForm;
import be.technifutur.technisandwich.repository.CartRepository;
import be.technifutur.technisandwich.repository.DietRepository;
import be.technifutur.technisandwich.repository.SandwichRepository;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.SandwichService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SandwichServiceImpl implements SandwichService {
    private final SandwichRepository sandwichRepository;
    private final UserRepository userRepository;
    private final DietRepository dietRepository;

    public SandwichServiceImpl(
            SandwichRepository sandwichRepository,
            UserRepository userRepository,
            DietRepository dietRepository) {
        this.sandwichRepository = sandwichRepository;
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;
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
    public void addToCart(long id, long qt, String email) {
        Sandwich sandwich = sandwichRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        Cart userCart = userRepository.findByUserMail(email)
                .orElseThrow(RessourceNotFoundException::new)
                .getCart();

        for(int i = 0; i < qt; i++) {
            userCart.getSandwiches().add(sandwich);
        }
    }

    @Override
    public List<SandwichDTO> getAllWithDiet(String diet) {
        return sandwichRepository.findAll().stream()
                .filter(s -> s.getDiets().contains(diet))
                .map(entity -> SandwichDTO.from(entity))
                .toList();

    }

    @Override
    public void create(SandwichForm form) {
        Sandwich sandwich = form.toEntity();

        //DIETS

        sandwichRepository.save(sandwich);
    }

    @Override
    public void update(long id, Map<String, String> mapValues) {
        if( mapValues == null || mapValues.isEmpty() )
            return;

        Sandwich sandwich = sandwichRepository.findById(id)
                .orElseThrow( RessourceNotFoundException::new );

        if(mapValues.containsKey("name"))
            sandwich.setName(mapValues.get("name"));
        if(mapValues.containsKey("desc"))
            sandwich.setDesc(mapValues.get("desc"));
        if(mapValues.containsKey("price"))
            sandwich.setPrice(Double.parseDouble(mapValues.get("price")));
        if(mapValues.containsKey("diets")){
            Set<Diet> diets = Arrays.stream(mapValues.get("diets").split(","))
                    .map(s -> dietRepository.findByName(s)
                            .orElseThrow(RessourceNotFoundException::new))
                    .collect(Collectors.toSet());
            sandwich.setDiets(diets);
        }

        sandwichRepository.save(sandwich);
    }

    @Override
    public void delete(long id) {
        sandwichRepository.deleteById(id);
    }
}
