package be.technifutur.technisandwich.model.form;

import be.technifutur.technisandwich.model.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RegistrationForm {
    @NotNull
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public User toEntity(){
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(Set.of("USER"));
        user.setEnable(true);
        user.setBlackListed(false);

        return user;
    }
}