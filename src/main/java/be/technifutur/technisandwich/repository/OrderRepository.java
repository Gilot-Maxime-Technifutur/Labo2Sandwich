package be.technifutur.technisandwich.repository;

import be.technifutur.technisandwich.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
