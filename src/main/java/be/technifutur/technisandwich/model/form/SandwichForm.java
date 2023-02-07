package be.technifutur.technisandwich.model.form;

import be.technifutur.technisandwich.model.entity.Sandwich;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SandwichForm {
    @NotBlank
    private String name;

    @NotNull
    private String desc;

    @NotNull
    private double price;

    public Sandwich toEntity(){
        Sandwich sandwich = new Sandwich();

        sandwich.setName(name);
        sandwich.setDesc(desc);
        sandwich.setPrice(price);

        return sandwich;
    }
}
