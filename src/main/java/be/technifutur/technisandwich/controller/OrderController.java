package be.technifutur.technisandwich.controller;

import be.technifutur.technisandwich.model.entity.Order;
import be.technifutur.technisandwich.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public String order(Authentication authentication){
        orderService.create((String) authentication.getPrincipal());

        return "order-created";
    }

    @PostMapping("/cancel}")
    public String cancel(@RequestParam long id){
        orderService.cancel(id);

        return "order-"+id+"-cancelled";
    }

    @PostMapping("/done}")
    public String done(@RequestParam long id){
        orderService.done(id);

        return "order-"+id+"delivered";
    }

    @PostMapping("/set")
    public String done(@RequestParam long id, @RequestParam String state){
        orderService.set(id, state);

        return "order-"+id+"-"+state;
    }

    @GetMapping("/view/all")
    public Set<Order> getAll(Authentication authentication){
        return orderService.getAll((String) authentication.getPrincipal());
    }

    @GetMapping("/view/{id:[0-9]+}")
    public Order getOne(@PathVariable long id, Authentication authentication) {
        return orderService.getOne(id, (String) authentication.getPrincipal());
    }

    @GetMapping("/all")
    public Set<Order> getAll(){
        return orderService.getAll();
    }
}
