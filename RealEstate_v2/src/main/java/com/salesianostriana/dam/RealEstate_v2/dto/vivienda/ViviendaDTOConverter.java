package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;


import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.GetViviendaInmobiliariaDto;
import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDTOConverter {

    String t = "";
    Tipo tipo = Tipo.VENTA;

    public Vivienda createViviendaSummarizedDtoToVivienda(CreateViviendaSummarizedDTO v){
        return new Vivienda (
                v.getTitulo(),
                v.getDescripcion(),
                v.getAvatar(),
                v.getPrecio(),
                v.getInteresas()
        );
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
        return new Vivienda (
                v.getTitulo(),
                v.getAvatar(),
                v.getTipo(),
                v.getPrecio(),
                v.getUbicacion(),
                v.getMetrosCuadrados(),
                v.getNumBanios(),
                v.getNumHabitaciones(),
                v.isTieneAscensor(),
                v.isTieneGaraje(),
                v.isTienePiscina(),
                v.getPropietario(),
                v.getInmobiliaria()
        );
    }

    public GetViviendaDTO viviendaToGetViviendaDTO(Vivienda v) {
        return GetViviendaDTO
                .builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .precio(v.getPrecio())
                .ubicacion(String.format("%s, %s", v.getDireccion(), v.getCodigoPostal()))
                .zona(String.format("%s (%s)", v.getPoblacion(), v.getProvincia()))
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .numHabitaciones(v.getNumHabitaciones())
                .avatar(v.getAvatar())
                .tipo(v.getTipoVivienda().getTexto())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .tienePiscina(v.isTienePiscina())
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
                .tipo(v.getTipoVivienda().getTexto())
                .zona(String.format("%s (%s)", v.getPoblacion(), v.getProvincia()))
                .ubicacion(String.format("%s, %s", v.getDireccion(), v.getCodigoPostal()))
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .avatar(v.getAvatar())
                .nombreInmo(v.getInmobiliaria().getNombre())
                .build();
    }



    public GetViviendaPropietarioDTO createViviendaPropietarioDTO (Vivienda v){
        return GetViviendaPropietarioDTO
                .builder()
                .id(v.getId())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .provincia(v.getProvincia())
                .poblacion(v.getPoblacion())
                .metrosCuadrados(v.getMetrosCuadrados())
                .propietario(v.getPropietario())
                .build();
    }

    public Usuario getPropietarioVivienda (GetViviendaPropietarioDTO gv){
        return Usuario.builder()
                .id(gv.getId())
                .build();
    }

    public Vivienda getViviendaPropietario (GetViviendaPropietarioDTO gv){
        return Vivienda.builder()
                .titulo(gv.getTitulo())
                .descripcion(gv.getDescripcion())
                .direccion(gv.getDireccion())
                .codigoPostal(gv.getCodigoPostal())
                .precio(gv.getPrecio())
                .poblacion(gv.getPoblacion())
                .provincia(gv.getProvincia())
                .latlng(gv.getLatlng())
                .tipoVivienda(gv.getTipo())
                .metrosCuadrados(gv.getMetrosCuadrados())
                .numBanios(gv.getNumBanios())
                .numHabitaciones(gv.getNumHabitaciones())
                .avatar(gv.getAvatar())
                .tieneAscensor(gv.isTieneAscensor())
                .tieneGaraje(gv.isTieneGaraje())
                .tienePiscina(gv.isTienePiscina())
                .build();
    }



}
