package com.bootcamp.facturacion.controllers.auth;

import com.bootcamp.facturacion.dto.UsuarioDTO;
import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.services.ActividadEconomicaService;
import com.bootcamp.facturacion.services.auth.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/usuarios") //ENDPOINT  / RECURSO
public class UsuarioController {

    @Autowired
    private UsuarioService servicio;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario guardar(@RequestBody UsuarioDTO usuario){
        return servicio.registrarUsuario(usuario);
    }

}
