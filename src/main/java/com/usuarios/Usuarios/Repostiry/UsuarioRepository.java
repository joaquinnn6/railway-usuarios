package com.usuarios.Usuarios.Repostiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usuarios.Usuarios.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // La interfaz JpaRepository ya proporciona métodos CRUD básicos
    // Puedes agregar consultas personalizadas si es necesario
}