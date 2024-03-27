package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Motivo;

public interface RepositorioDeMotivo extends JpaRepository<Motivo, String> {
}
