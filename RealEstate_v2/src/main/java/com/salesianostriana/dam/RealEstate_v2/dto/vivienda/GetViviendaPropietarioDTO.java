package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;


import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetViviendaPropietarioDTO {


    private UUID id;
    private String titulo, descripcion, avatar;
    private String direccion, codigoPostal, provincia, poblacion, latlng;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private Tipo tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    private Usuario propietario;
}
