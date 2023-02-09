package be.technifutur.technisandwich.controller;

import be.technifutur.technisandwich.model.entity.User;
import be.technifutur.technisandwich.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/view/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/view/{id:[0-9]+}")
    public User getOne(@PathVariable long id){
        return userService.getOne(id);
    }
}
