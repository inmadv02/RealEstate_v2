package com.salesianostriana.dam.RealEstate_v2.users.controller;

import com.salesianostriana.dam.RealEstate_v2.users.dto.CreateUsuarioDTO;
import com.salesianostriana.dam.RealEstate_v2.users.dto.GetUsuarioDTO;
import com.salesianostriana.dam.RealEstate_v2.users.dto.UsuarioDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import com.salesianostriana.dam.RealEstate_v2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/auth/register")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDTOConverter usuarioDTOConverter;

    @PostMapping("/admin")
    public ResponseEntity<GetUsuarioDTO> nuevoUsuario(@RequestBody CreateUsuarioDTO nuevoUsuario){
        Usuario guardado = usuarioService.save(nuevoUsuario);

        if (guardado == null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(usuarioDTOConverter.usuarioTOGetUsuarioDTO(guardado));
        }
    }

    @PostMapping("/propietario")
    public ResponseEntity<GetUsuarioDTO> nuevoPropietario(@RequestBody CreateUsuarioDTO nuevoUsuario){
        Usuario guardado = usuarioService.savePropietario(nuevoUsuario);

        if (guardado == null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(usuarioDTOConverter.usuarioTOGetUsuarioDTO(guardado));
        }
    }

    @PostMapping("/gestor")
    public ResponseEntity<GetUsuarioDTO> nuevoGestor(@RequestBody CreateUsuarioDTO nuevoUsuario){
        Usuario guardado = usuarioService.saveGestor(nuevoUsuario);

        if (guardado == null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(usuarioDTOConverter.usuarioTOGetUsuarioDTO(guardado));
        }
    }
}
