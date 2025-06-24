package com.usuarios.Usuarios.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.usuarios.Usuarios.Model.Usuario;
import com.usuarios.Usuarios.Service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<EntityModel<Usuario>> usuariosModel = usuarios.stream()
            .map(usuario -> EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getIdUsuarios())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .collect(Collectors.toList());
        return ResponseEntity.ok(
            CollectionModel.of(usuariosModel, linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel())
        );
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(
                EntityModel.of(usuario,
                    linkTo(methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel(),
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios"))
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear un usuario")
    @PostMapping("/ingresar")
    public ResponseEntity<EntityModel<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity
            .created(linkTo(methodOn(UsuarioController.class).getUsuarioById(createdUsuario.getIdUsuarios())).toUri())
            .body(EntityModel.of(createdUsuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(createdUsuario.getIdUsuarios())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}