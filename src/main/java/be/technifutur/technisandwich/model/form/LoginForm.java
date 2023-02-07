package be.technifutur.technisandwich.model.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
