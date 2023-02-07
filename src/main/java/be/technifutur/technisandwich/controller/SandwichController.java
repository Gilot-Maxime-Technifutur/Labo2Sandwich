package be.technifutur.technisandwich.controller;

import be.technifutur.technisandwich.model.dto.SandwichDTO;
import be.technifutur.technisandwich.model.form.SandwichForm;
import be.technifutur.technisandwich.service.SandwichService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/sandwich")
public class SandwichController {
    private final SandwichService sandwichService;

    public SandwichController(SandwichService sandwichService) {
        this.sandwichService = sandwichService;
    }

    @GetMapping("/view/all")
    public List<SandwichDTO> getAll(){
        return sandwichService.getAll();
    }

    @GetMapping("/view/{id:[0-9]+}")
    public SandwichDTO getOne(@PathVariable long id){
        return sandwichService.getOne(id);
    }

    @GetMapping("/view/{diet}")
    public List<SandwichDTO> getAllWithDiet(@PathVariable String diet){
        return sandwichService.getAllWithDiet(diet);
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam long id, @RequestParam long qt, Authentication authentication) {
        sandwichService.addToCart(id, qt, (String) authentication.getPrincipal());

        return "added-to-cart";
    }

    @PostMapping("/add")
    public void create(@RequestBody @Valid SandwichForm form){
        sandwichService.create( form );
    }

    @PatchMapping(path = "/{id:[0-9]+}/update")
    public void update(@PathVariable long id, @RequestParam Map<String, String> params){
        Map<String, String> mapValues = new HashMap<>();
        if(params.containsKey("name"))
            mapValues.put("name", params.get("name"));
        if(params.containsKey("desc"))
            mapValues.put("desc", params.get("desc"));
        if(params.containsKey("price"))
            mapValues.put("price", params.get("price"));
        if(params.containsKey("diets"))
            mapValues.put("diets", params.get("diets"));

        sandwichService.update( id, mapValues );
    }

    @DeleteMapping({"/{id:[0-9]+}", "/{id:[0-9]+}/delete"})
    public void delete(@PathVariable long id){
        sandwichService.delete(id);
    }
}
