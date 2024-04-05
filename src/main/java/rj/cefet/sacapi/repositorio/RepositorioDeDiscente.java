package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import rj.cefet.sacapi.modelo.Discente;

import java.util.UUID;

public interface RepositorioDeDiscente extends JpaRepository<Discente, String> {
    UserDetails findByMatricula(String matricula);
}
