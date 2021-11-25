package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;

import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateViviendaDTO {

    private Long id;
    private String titulo, descripcion, avatar;
    private String direccion, latlng, provincia, poblacion, codigoPostal;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private Tipo tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    @Column(nullable = true)
    private Long inmobiliaria;
}
