package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;

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

    public GetInmobiliariaViviendaDTO(UUID id, String titulo, String descripcion, String avatar, String ubicacion, String zona, int numHabitaciones, int numBanios, double metrosCuadrados, double precio, String tipo, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje, String propietario, String inmobiliaria, List<String> nombreVivienda) {
        super(id, titulo, descripcion, avatar, ubicacion, zona, numHabitaciones, numBanios, metrosCuadrados, precio, tipo, tienePiscina, tieneAscensor, tieneGaraje, propietario, inmobiliaria);
        this.nombreVivienda = nombreVivienda;
    }
}
