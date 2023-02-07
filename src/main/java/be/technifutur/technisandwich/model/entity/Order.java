package be.technifutur.technisandwich.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "deliver_date")
    private LocalDateTime deliverDate;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private String state;
    // DONE
    // DELIVER
    // PREPARE
    // ON_HOLD
    // CANCELLED

    @ManyToMany
    @JoinTable(name = "order_sandwich",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "sandwich_id"))
    private List<Sandwich> sandwiches = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
