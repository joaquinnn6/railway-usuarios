package com.usuarios.Usuarios;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // <-- Faltaba este import

@SpringBootApplication
@ComponentScan(basePackages = "com.usuarios.Usuarios")
public class UsuariosApplication {
    public static void main(String[] args) {
        // ⚠️ Configura el wallet ANTES de que Spring arranque
        Path walletPath = Paths.get("src/main/resources/wallet");
        System.setProperty("oracle.net.tns_admin", walletPath.toAbsolutePath().toString());
        System.out.println("[MAIN] TNS_ADMIN configurado en: " + walletPath.toAbsolutePath());

        SpringApplication.run(UsuariosApplication.class, args); // <-- Corrige el nombre de la clase aquí
    }
}
