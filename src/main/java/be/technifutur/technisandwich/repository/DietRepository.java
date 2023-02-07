package be.technifutur.technisandwich.repository;

import be.technifutur.technisandwich.model.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietRepository extends JpaRepository<Diet, Long> {
    Optional<Diet> findByName(String name);
}
