package be.technifutur.technisandwich.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Sandwich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sandwich_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String desc;

    @Column(nullable = false)
    private Double price;
}
