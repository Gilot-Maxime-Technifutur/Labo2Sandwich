package be.technifutur.technisandwich.repository;

import be.technifutur.technisandwich.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
    boolean existsByEmail(String username);
}