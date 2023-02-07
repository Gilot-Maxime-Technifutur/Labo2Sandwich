package be.technifutur.technisandwich.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "\"user\"")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Set<String> roles;
    // USER
    // ADMIN
    // SHIPPER / WORKER ?

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Boolean enable;

    @Column(nullable = false)
    private Boolean blackListed;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
