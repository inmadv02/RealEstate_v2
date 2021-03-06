package com.salesianostriana.dam.RealEstate_v2.dto.interesa;

import com.salesianostriana.dam.RealEstate_v2.model.Interesa;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInteresadoDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String avatar;
    private String mensaje;
    private Date createdDate;

    public GetInteresadoDTO(Long id, String nombre, String apellidos, String direccion, String email, String telefono, String avatar) {
    }
}
