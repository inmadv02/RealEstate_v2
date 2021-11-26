package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;

import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class GetInmobiliariaViviendaDTO extends GetViviendaDTO {

    private List<String> nombreVivienda;

    public GetInmobiliariaViviendaDTO(Long id, String titulo, String descripcion, String avatar, String direccion, String latlng, String provincia, String poblacion, String codigoPostal, int numHabitaciones, int numBanios, double metrosCuadrados, double precio, Tipo tipo, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, String propietario, String inmobiliaria, List<String> nombreVivienda) {
        super(id, titulo, descripcion, avatar, direccion, latlng, provincia, poblacion, codigoPostal, numHabitaciones, numBanios, metrosCuadrados, precio, tipo, tienePiscina, tieneAscensor, tieneGaraje, propietario, inmobiliaria);
        this.nombreVivienda = nombreVivienda;
    }
}
