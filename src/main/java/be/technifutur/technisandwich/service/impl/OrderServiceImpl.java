package be.technifutur.technisandwich.service.impl;

import be.technifutur.technisandwich.exception.RessourceNotFoundException;
import be.technifutur.technisandwich.model.entity.Order;
import be.technifutur.technisandwich.model.entity.User;
import be.technifutur.technisandwich.repository.OrderRepository;
import be.technifutur.technisandwich.repository.UserRepository;
import be.technifutur.technisandwich.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(String email) {
        User user = userRepository.findByUserMail(email)
                .orElseThrow(RessourceNotFoundException::new);

        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());
        order.setDiscount(0d);
        order.setState("GOING");
        order.setSandwiches(user.getCart().getSandwiches());

        orderRepository.save(order);

        user.getCart().setSandwiches(new ArrayList<>());
    }

    @Override
    public void cancel(long id) {
        orderRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new)
                .setState("CANCELLED");
    }

    @Override
    public void done(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        order.setState("DONE");
        order.setDeliverDate(LocalDateTime.now());
    }

}
