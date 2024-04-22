package rj.cefet.sacapi.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Comentario;

import java.util.List;
import java.util.UUID;

public interface RepositorioDeComentario extends JpaRepository<Comentario, UUID> {
    Page<Comentario> findByChamado(Chamado chamado, Pageable pageable);
}
