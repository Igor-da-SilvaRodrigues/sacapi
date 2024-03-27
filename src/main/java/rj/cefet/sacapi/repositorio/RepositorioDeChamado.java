package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Chamado;

import java.util.UUID;

public interface RepositorioDeChamado extends JpaRepository<Chamado, String> {
}
