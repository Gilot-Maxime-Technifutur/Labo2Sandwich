package be.technifutur.technisandwich.model.dto;

import be.technifutur.technisandwich.model.entity.Sandwich;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SandwichDTO {
    private Long id;
    private String name;
    private String desc;

    public static SandwichDTO from(Sandwich entity){
        if(entity == null)
            return null;

        return SandwichDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .desc(entity.getDesc())
                .build();
    }
}
