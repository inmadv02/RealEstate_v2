package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;


import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.GetViviendaInmobiliariaDto;
import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDTOConverter {

    public Vivienda createViviendaSummarizedDtoToVivienda(CreateViviendaSummarizedDTO v){
        return Vivienda
                .builder()
                .titulo(v.getTitulo())
                .precio(v.getPrecio())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .build();
    }

    public GetViviendaSummarizedDTO viviendaToGetViviendaSummarizedDTO(Vivienda m) {
        return GetViviendaSummarizedDTO
                .builder()
                .id(m.getId())
                .titulo(m.getTitulo())
                .descripcion(m.getDescripcion())
                .avatar(m.getAvatar())
                .precio(m.getPrecio())
                .interesas(m.getInteresas().size())
                .build();

    }

    public Vivienda createViviendaDtoToVivienda(CreateViviendaDTO v){
        return  Vivienda
                .builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .direccion(v.getDireccion())
                .latlng(v.getLatlng())
                .codigoPostal(v.getCodigoPostal())
                .provincia(v.getProvincia())
                .poblacion(v.getPoblacion())
                .precio(v.getPrecio())
                .tipoVivienda(v.getTipo())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .build();

    }

    public GetViviendaDTO viviendaToGetViviendaDTO(Vivienda v) {
        return GetViviendaDTO
                .builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .direccion(v.getDireccion())
                .latlng(v.getLatlng())
                .codigoPostal(v.getCodigoPostal())
                .provincia(v.getProvincia())
                .poblacion(v.getPoblacion())
                .precio(v.getPrecio())
                .tipo(v.getTipoVivienda())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .propietario(v.getPropietario()==null?null:v.getPropietario().getNombre())
                .inmobiliaria(v.getInmobiliaria()==null?null:v.getInmobiliaria().getNombre())
                .build();
    }


    public GetViviendaInmobiliariaDto GetInmobiliariaViviendaDto(Vivienda v){


        return GetViviendaInmobiliariaDto.builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .precio(v.getPrecio())
                .propietario(v.getPropietario().getNombre())
                .tipo(v.getTipoVivienda())
                .direccion(v.getDireccion())
                .latlng(v.getLatlng())
                .codigoPostal(v.getCodigoPostal())
                .provincia(v.getProvincia())
                .poblacion(v.getPoblacion())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .avatar(v.getAvatar())
                .nombreInmo(v.getInmobiliaria().getNombre())
                .build();
    }







}
