package be.technifutur.technisandwich.repository;

import be.technifutur.technisandwich.model.entity.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SandwichRepository extends JpaRepository<Sandwich, Long> {
}
