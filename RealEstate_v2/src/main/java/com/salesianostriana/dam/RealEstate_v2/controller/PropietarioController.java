package com.salesianostriana.dam.RealEstate_v2.controller;



import com.salesianostriana.dam.RealEstate_v2.dto.propietario.GetPropietarioDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.propietario.GetPropietarioViviendaDto;
import com.salesianostriana.dam.RealEstate_v2.dto.propietario.PropietarioDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.security.AuthenticationController;
import com.salesianostriana.dam.RealEstate_v2.users.controller.UsuarioController;
import com.salesianostriana.dam.RealEstate_v2.users.dto.UsuarioDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.users.model.RolUsuario;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import com.salesianostriana.dam.RealEstate_v2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/propietario")
public class PropietarioController {

    private final UsuarioService propietarioService;
    private final PropietarioDTOConverter propietarioDTOConverter;
    private final UsuarioController usuarioController;
    private final AuthenticationController authenticationController;


   /* @Operation(summary = "Borra un Propietario creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el propietario",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        if(propietarioService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        else {
            propietarioService.deleteById(id);

            return ResponseEntity.noContent().build();
        }


    }


    */
    @Operation(summary = "Buscamos todos los propietarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetPropietarioDTO>> findAll(){

        Optional <Usuario> datos = propietarioService.usuariosPropietarios(RolUsuario.PROPIETARIO);

        if(!datos.isEmpty()){
            List<GetPropietarioDTO> lista = datos.stream()
                    .map(propietarioDTOConverter::propietarioToGetPropietarioDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<GetPropietarioViviendaDto>> findOne(@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario){
        Optional<Usuario> prop = propietarioService.findById(id);

        if(usuario.getId().equals(id)){
            List<GetPropietarioViviendaDto> propietarioDTOS= prop.stream()
                    .map(propietarioDTOConverter::propietarioToGetPropietarioViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(propietarioDTOS);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
