package rj.cefet.sacapi.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Discente;

import java.util.UUID;

public interface RepositorioDeChamado extends JpaRepository<Chamado, String> {
    Page<Chamado> findByDiscente(Discente discente, Pageable pageable);
}
