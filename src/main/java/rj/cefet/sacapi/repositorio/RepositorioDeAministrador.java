package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import rj.cefet.sacapi.modelo.Administrador;

import java.util.UUID;

public interface RepositorioDeAministrador extends JpaRepository<Administrador, String> {
    UserDetails findByMatricula(String matricula);
}
