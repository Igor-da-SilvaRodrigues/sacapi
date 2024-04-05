package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import rj.cefet.sacapi.modelo.Usuario;

import java.util.UUID;

public interface RepositorioDeUsuario extends JpaRepository<Usuario, String> {
    UserDetails findByMatricula(String matricula);
}
