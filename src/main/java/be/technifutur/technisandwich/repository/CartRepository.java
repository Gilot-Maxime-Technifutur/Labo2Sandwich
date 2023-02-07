package be.technifutur.technisandwich.repository;

import be.technifutur.technisandwich.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
