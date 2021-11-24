package com.salesianostriana.dam.RealEstate_v2.dto.propietario;

import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import com.salesianostriana.dam.RealEstate_v2.users.dto.GetUsuarioDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GetPropietarioDTO extends GetUsuarioDTO {

    private List <String> viviendas;

    public GetPropietarioDTO(UUID id, String nombre, String email, String avatar, String rol, List<String> viviendas) {
        super(id, nombre, email, avatar, rol);
        this.viviendas = viviendas;
    }
}
