package com.usuarios.Usuarios.Service;
import java.util.List;

import com.usuarios.Usuarios.Model.Usuario;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Long id);
    Usuario createUsuario(Usuario usuario);
    Usuario updateUsuario(Long id, Usuario usuario);
    void deleteUsuario(Long id);
}
