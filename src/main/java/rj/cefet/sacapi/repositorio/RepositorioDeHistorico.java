package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Historico;

import java.util.UUID;

public interface RepositorioDeHistorico extends JpaRepository<Historico, UUID> {
}
