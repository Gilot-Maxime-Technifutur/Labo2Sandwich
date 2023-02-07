package be.technifutur.technisandwich.controller;

import be.technifutur.technisandwich.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/cancel/{id:[0-9]+}")
    public String cancel(@PathVariable long id){
        orderService.cancel(id);

        return "order-"+id+"-cancelled";
    }

    @PostMapping("/done/{id:[0-9]+}")
    public String done(@PathVariable long id){
        orderService.done(id);

        return "order-"+id+"delivered";
    }
}
