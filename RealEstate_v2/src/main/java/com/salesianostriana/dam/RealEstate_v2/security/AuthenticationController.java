package com.salesianostriana.dam.RealEstate_v2.security;

import com.salesianostriana.dam.RealEstate_v2.security.dto.JwtUsuarioResponse;
import com.salesianostriana.dam.RealEstate_v2.security.dto.LoginDTO;
import com.salesianostriana.dam.RealEstate_v2.security.jwt.JwtProvider;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(convertUserToJwtUserResponse(usuario, jwt));
    }

    @GetMapping("/me")
    public ResponseEntity<?> who(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(convertUserToJwtUserResponse(usuario, null));
    }

    private JwtUsuarioResponse convertUserToJwtUserResponse(Usuario usuario, String jwt) {
        return JwtUsuarioResponse.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .avatar(usuario.getAvatar())
                .rol(usuario.getRol().name())
                .token(jwt)
                .build();
    }

}
