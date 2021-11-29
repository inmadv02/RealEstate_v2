package com.salesianostriana.dam.RealEstate_v2.dto.interesa;

import com.salesianostriana.dam.RealEstate_v2.model.Interesa;
import com.salesianostriana.dam.RealEstate_v2.users.dto.GetUsuarioDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInteresadoDTO extends GetUsuarioDTO {

    private String mensaje;
    private Date createdDate;

    public GetInteresadoDTO(UUID id, String nombre, String email, String avatar, String rol, String mensaje, Date createdDate) {
        super(id, nombre, email, avatar, rol);
        this.mensaje = mensaje;
        this.createdDate = createdDate;
    }
}
