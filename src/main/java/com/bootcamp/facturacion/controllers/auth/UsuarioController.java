package com.bootcamp.facturacion.controllers.auth;

import com.bootcamp.facturacion.dto.UsuarioDTO;
import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.services.auth.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios y autenticación")
public class UsuarioController {

    @Autowired
    private UsuarioService servicio;

    @Operation(summary = "Registrar un usuario",
            description = "Registra un nuevo usuario en el sistema con contraseña encriptada")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario ya existe")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario guardar(@RequestBody UsuarioDTO usuario){

        return servicio.registrarUsuario(usuario);
    }

}
