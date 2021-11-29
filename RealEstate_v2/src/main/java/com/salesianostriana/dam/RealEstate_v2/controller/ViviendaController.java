package com.salesianostriana.dam.RealEstate_v2.controller;

import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.GetInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.GetViviendaInmobiliariaDto;
import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.InmobiliariaDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.dto.interesa.GetInteresaDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.interesa.GetInteresadoDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.interesa.InteresaDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.dto.vivienda.*;
import com.salesianostriana.dam.RealEstate_v2.repositories.InmobiliariaRepository;
import com.salesianostriana.dam.RealEstate_v2.repositories.ViviendaRepository;
import com.salesianostriana.dam.RealEstate_v2.services.InmobiliariaService;
import com.salesianostriana.dam.RealEstate_v2.model.*;
import com.salesianostriana.dam.RealEstate_v2.services.InteresaService;
import com.salesianostriana.dam.RealEstate_v2.services.ViviendaService;
import com.salesianostriana.dam.RealEstate_v2.users.model.RolUsuario;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import com.salesianostriana.dam.RealEstate_v2.users.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Vivienda", description = "Controller de las viviendas")
@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final ViviendaDTOConverter viviendaDTOConverter;
    private final InteresaService interesaService;
    private final ViviendaRepository viviendaRepository;
    private final InmobiliariaService inmobiliariaService;
    private final InteresaDTOConverter interesaDTOConverter;
    private final ViviendaDTOConverter dtoConverter;
    private final UsuarioService propietarioService;


    @Operation(summary = "Se listan todas las viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las viviendas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado las viviendas",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetViviendaSummarizedDTO>> findAll(@PageableDefault(page=0, size=9) Pageable pageable){

        Page<Vivienda> datos = viviendaService.findAll(pageable);

        if(datos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            List<GetViviendaSummarizedDTO> lista = datos.stream()
                    .map(viviendaDTOConverter::viviendaToGetViviendaSummarizedDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }
    }

    @GetMapping("/propietario")
    public ResponseEntity<List<GetViviendaSummarizedDTO>> findAllByPropietario(@AuthenticationPrincipal Usuario propietario){

        List <Vivienda> datos = viviendaService.encontrarViviendasPropietario(propietario.getId());

        if(datos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            List<GetViviendaSummarizedDTO> lista = datos.stream()
                    .map(viviendaDTOConverter::viviendaToGetViviendaSummarizedDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }



    }


    @Operation(summary = "Crea una nueva vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la vivienda",
                    content = @Content),
    })

    @PostMapping("/")
    public ResponseEntity<CreateViviendaDTO> createVivienda (@RequestBody CreateViviendaDTO dto,
                                                                     @AuthenticationPrincipal Usuario propietario){

        if(dto.getTitulo().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        else{

            viviendaService.addVivienda(dto, propietario);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);


        }

    }

    @Operation(summary = "Crea un nuevo interesado e interesa asociado a una vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el interesado y el interesa asociado a una vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado el interesado y el interesa asociado a una vivienda",
                    content = @Content),
    })
    @PostMapping("/{id}/meinteresa")
    public ResponseEntity<GetInteresaDTO> createInteresa (@RequestBody GetInteresadoDTO dto, @PathVariable Long id){



    }




    @Operation(summary = "Obtiene una vivienda creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda",
                    content = @Content),
    })
    @GetMapping("/{id}")
        public ResponseEntity<GetViviendaDTO> findOne(@PathVariable Long id) {

        Optional<Vivienda> vivienda = viviendaService.findById(id);

        if(vivienda.isEmpty()){
            return ResponseEntity.notFound().build();

        }else {
            GetViviendaDTO viviendaDTO = viviendaDTOConverter.viviendaToGetViviendaDTO(vivienda.get());
            return ResponseEntity.ok().body(viviendaDTO);
        }
    }


    @Operation(summary = "Obtiene un top de las 5 viviendas con más interesados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las viviendas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })


    @GetMapping("/top")
    public ResponseEntity<List<GetViviendaSummarizedDTO>> top10Viviendas (@RequestParam("n") int n) {
        List<Vivienda> datos = viviendaService.findTop10ViviendaOrderByInteresas();

        List<GetViviendaSummarizedDTO> lista = datos.stream()
                .map(viviendaDTOConverter::viviendaToGetViviendaSummarizedDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(lista);


    }
/*
    @Operation(summary = "Borra un interesa asociado a una vivienda y a un interesado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el interesa asociado a una vivienda y a un interesado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })


    @DeleteMapping("/{id1}/meinteresa/{id2}")
    public ResponseEntity<?> delete(@PathVariable Long id1, @PathVariable Long id2) {

    if(viviendaService.findById(id1).isEmpty() && interesadoService.findById(id2).isEmpty()){
        return ResponseEntity.notFound().build();
    } else {

        List<Interesa> interesaList = viviendaService.getById(id1).getInteresas();
        Vivienda vivienda = viviendaService.getById(id1);
        Interesado interesado = interesadoService.getById(id2);

        for (Interesa i : interesaList){
            interesaService.delete(i);

            return ResponseEntity.noContent().build();
        }
    }
    return ResponseEntity.badRequest().build();
    }




*/


    @Operation(summary = "Edita una vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha cambiado la vivienda y se ha guardado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la vivienda",
                    content = @Content),
    })

    @PutMapping("/{id}")
    public ResponseEntity<GetViviendaDTO> edit (@RequestBody GetViviendaDTO v,
                                          @PathVariable Long id,
                                          @AuthenticationPrincipal Usuario propietario) {

        Optional <Vivienda> viviendaEditada = viviendaService.findById(id);

        if(viviendaEditada.isEmpty()){
            return ResponseEntity.notFound().build();

        }

        if (!viviendaEditada.get().getPropietario().getId().equals(propietario.getId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        else {
            return ResponseEntity.of(

                    viviendaService.findById(id).map(m -> {
                        m.setTitulo(v.getTitulo());
                        m.setDescripcion(v.getDescripcion());
                        m.setAvatar(v.getAvatar());
                        m.setCodigoPostal(v.getCodigoPostal());
                        m.setLatlng(v.getLatlng());
                        m.setMetrosCuadrados(v.getMetrosCuadrados());
                        m.setNumBanios(v.getNumBanios());
                        m.setNumHabitaciones(v.getNumHabitaciones());
                        m.setPoblacion(v.getPoblacion());
                        m.setPrecio(v.getPrecio());
                        m.setProvincia(v.getProvincia());
                        m.setDireccion(v.getDireccion());
                        m.setTipoVivienda(v.getTipo());
                        m.setTienePiscina(v.isTienePiscina());
                        m.setTieneAscensor(v.isTieneAscensor());
                        m.setTieneGaraje(v.isTieneGaraje());
                        viviendaService.save(m);

                        return dtoConverter.viviendaToGetViviendaDTO(m);
                    })

            );


        }
    }



    @Operation(summary = "Borra una vivienda por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id, @AuthenticationPrincipal Usuario propietario) {

        Optional <Vivienda> vivienda = viviendaService.findById(id);

        if(vivienda.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if (!vivienda.get().getPropietario().getId().equals(propietario.getId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        else {
            viviendaService.deleteById(id);

            return ResponseEntity.noContent().build();
        }


    }

    @Operation(summary = "Se asocia una vivienda a una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la asociación",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la asociación",
                    content = @Content),
    })

    @PostMapping("{id}/inmobiliaria/{id2}")
    public ResponseEntity<GetViviendaDTO> establecerGestionInmobiliaria(@PathVariable Long id,
                                                                        @PathVariable Long id2,
                                                                        @AuthenticationPrincipal Usuario usuario) {
        Optional<Vivienda> vivienda = viviendaService.findById(id);
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id2);

        if (vivienda.isEmpty() || inmobiliaria.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            if (usuario.getRol().equals(RolUsuario.ADMIN) || vivienda.get().getPropietario().getId().equals(usuario.getId())) {

                vivienda.get().addToInmobiliaria(inmobiliaria.get());

                viviendaService.save(vivienda.get());
                inmobiliariaService.save(inmobiliaria.get());

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(viviendaDTOConverter.viviendaToGetViviendaDTO(vivienda.get()));
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }



    @Operation(summary = "Borra la asociación entre una vivienda y una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la vivienda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la vivienda a la que le queremos quitar la inmobiliaria",
                    content = @Content),
    })

    @DeleteMapping("/{id}/inmobiliaria/")
    public ResponseEntity deleteInmobiliariaFromVivienda(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {

        Optional<Vivienda> vivienda = viviendaService.findById(id);

        if(usuario.getRol().equals(RolUsuario.ADMIN) || vivienda.get().getPropietario().equals(usuario.getId())){

            Inmobiliaria inmobiliaria = vivienda.get().getInmobiliaria();
            vivienda.get().removeFromInmobiliaria(inmobiliaria);
            viviendaService.save(vivienda.get());

            return ResponseEntity.noContent().build();

        }

        else if (vivienda.isEmpty()){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .build();



    }





    

}
