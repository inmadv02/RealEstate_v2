package com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria;

import com.salesianostriana.dam.RealEstate_v2.dto.vivienda.GetViviendaDTO;
import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class GetViviendaInmobiliariaDto extends GetViviendaDTO {

    private String nombreInmo;

    public GetViviendaInmobiliariaDto(Long id, String titulo, String descripcion, String avatar, String direccion, String latlng, String provincia, String poblacion, String codigoPostal, int numHabitaciones, int numBanios, double metrosCuadrados, double precio, Tipo tipo, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, String propietario, String inmobiliaria, String nombreInmo) {
        super(id, titulo, descripcion, avatar, direccion, latlng, provincia, poblacion, codigoPostal, numHabitaciones, numBanios, metrosCuadrados, precio, tipo, tienePiscina, tieneAscensor, tieneGaraje, propietario, inmobiliaria);
        this.nombreInmo = nombreInmo;
    }
}