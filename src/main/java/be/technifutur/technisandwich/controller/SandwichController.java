package be.technifutur.technisandwich.controller;

import be.technifutur.technisandwich.model.dto.SandwichDTO;
import be.technifutur.technisandwich.service.SandwichService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add/{id:[0-9]+}")
    public String addToCart(@PathVariable long id, Authentication authentication){
        sandwichService.addToCart(id, (String) authentication.getPrincipal());

        return "view-all";
    }
}
