package com.usuarios.Usuarios.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "USUARIOS")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @Column(name = "ID_USUARIOS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Nota: Si Oracle usa secuencias, considera cambiar a:
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    // @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    private Long idUsuarios;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "CARGO")
    private String cargo;
}
