package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Discente;

import java.util.UUID;

public interface RepositorioDeDiscente extends JpaRepository<Discente, UUID> {
}
