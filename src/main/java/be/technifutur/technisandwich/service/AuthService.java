package be.technifutur.technisandwich.service;

import be.technifutur.technisandwich.jwt.JwtHolderDTO;
import be.technifutur.technisandwich.model.form.LoginForm;
import be.technifutur.technisandwich.model.form.RegistrationForm;

public interface AuthService {
    void register(RegistrationForm form);
    JwtHolderDTO login(LoginForm form);
}
