package com.salesianostriana.dam.RealEstate_v2.controller;


import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.CreateInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.GetInmobiliariaDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria.InmobiliariaDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import com.salesianostriana.dam.RealEstate_v2.repositories.InmobiliariaRepository;
import com.salesianostriana.dam.RealEstate_v2.services.InmobiliariaService;
import com.salesianostriana.dam.RealEstate_v2.users.dto.CreateUsuarioDTO;
import com.salesianostriana.dam.RealEstate_v2.users.dto.GetUsuarioDTO;
import com.salesianostriana.dam.RealEstate_v2.users.dto.UsuarioDTOConverter;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@Tag(name = "Inmobiliaria", description = "Controller de las inmobiliarias")
@RestController
@RequiredArgsConstructor
@RequestMapping("/inmobiliaria")
public class InmobiliariaController {

    private final InmobiliariaDTOConverter inmobiliariaDtoConverter;
    private final InmobiliariaService inmobiliariaService;
    private final UsuarioService usuarioService;
    private final UsuarioDTOConverter usuarioDTOConverter;


    @Operation(summary = "Crea una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha creado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la inmobiliaria",
                    content = @Content),
    })


    @PostMapping("/")
    public ResponseEntity<Inmobiliaria> create (@RequestBody CreateInmobiliariaDTO inmobiliaria){


        if(inmobiliaria.getNombre().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Inmobiliaria inmobiliariaNueva = inmobiliariaDtoConverter.createInmobiliariaDtoToInmobiliaria(inmobiliaria);

        return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(inmobiliariaService.save(inmobiliariaNueva));

    }




    @Operation(summary = "Obtiene todos las inmobiliarias creadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las inmobiliarias",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado las inmobiliarias",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetInmobiliariaDTO>> findAll(){
        List <Inmobiliaria> datos= inmobiliariaService.findAll();

        if (datos.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            List<GetInmobiliariaDTO> lista = datos.stream()
                    .map(inmobiliariaDtoConverter::getInmobiliariaToInmobiliariaDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }

    }

    @Operation(summary = "Obtiene una inmobiliaria creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado la inmobiliaria",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetInmobiliariaDTO> findOne (@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        Optional<Inmobiliaria> inmo = inmobiliariaService.findById(id);
        Optional <Usuario> datos = usuarioService.usuariosPorRol(RolUsuario.GESTOR);

        if(inmo.isPresent() || inmo.isPresent() && datos.get().getInmobiliaria().getId().equals(id)) {
            GetInmobiliariaDTO inmobiliariaDTO= inmobiliariaDtoConverter.getInmobiliariaToInmobiliariaDto(inmo.get());
            return ResponseEntity.ok().body(inmobiliariaDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Borra una inmobiliaria creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la inmobiliaria",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<GetInmobiliariaDTO> borrarInmobiliaria(@PathVariable Long id){

        if(inmobiliariaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();

        } else {
            inmobiliariaService.deleteById(id);
                return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Se obtienen los gestores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se obtienen los gestores",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los gestores",
                    content = @Content),
    })

    @GetMapping("{id}/gestor")
    public ResponseEntity<List<GetUsuarioDTO>> findAllGestores(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){

        Optional <Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);
        List<Usuario> lista = inmobiliariaService.findById(id).get().getGestores();

        if (inmobiliaria.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (usuario.getRol().equals(RolUsuario.ADMIN) || usuario.getInmobiliaria().equals(inmobiliaria.get())) {
            List <GetUsuarioDTO> lista2 = lista.stream()
                    .map(usuarioDTOConverter::usuarioTOGetUsuarioDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista2);

        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();



    }

    @Operation(summary = "Crea un nuevo gestor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el gestor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el gestor",
                    content = @Content),
    })
    @PostMapping("/{id}/gestor")
    public ResponseEntity<GetUsuarioDTO> addGestor (@PathVariable Long id, @RequestBody CreateUsuarioDTO gestor,
                                                    @AuthenticationPrincipal Usuario usuario) {

        Optional<Inmobiliaria> inmo = inmobiliariaService.findById(id);


        if(inmo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else if (usuario.getRol().equals(RolUsuario.ADMIN) || usuario.getInmobiliaria().getId().equals(id)){

            Usuario getUsuarioDTO = usuarioService.saveGestor(gestor);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(usuarioDTOConverter
                            .usuarioTOGetUsuarioDTO(getUsuarioDTO));

        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @Operation(summary = "Borra un gestor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el gestor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})
    })

    @DeleteMapping("/gestor/{id}")
    public ResponseEntity<GetUsuarioDTO> removeGestor (@PathVariable UUID id, @AuthenticationPrincipal Usuario gestor){

        Optional<Usuario> gestorEliminado = usuarioService.findById(id);


        if (gestor.getRol().equals(RolUsuario.ADMIN) || gestorEliminado.get().getInmobiliaria().getId().equals(gestor.getInmobiliaria().getId())) {
            usuarioService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        else {
            return ResponseEntity.notFound().build();
        }
    }





}
