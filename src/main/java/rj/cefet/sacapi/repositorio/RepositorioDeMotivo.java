package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.MotivoId;

public interface RepositorioDeMotivo extends JpaRepository<Motivo, MotivoId> {
}
