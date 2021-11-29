package com.salesianostriana.dam.RealEstate_v2.dto.interesa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInteresaDTO {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String avatar;
    private String mensaje;
    private LocalDateTime createdDate;
}
