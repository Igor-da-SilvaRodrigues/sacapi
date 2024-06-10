package rj.cefet.sacapi.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Discente;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RepositorioDeChamado extends JpaRepository<Chamado, String> {
    Page<Chamado> findByDiscente(Discente discente, Pageable pageable);

    /**
     * Retorna chamados em um estado específico
     */
    Page<Chamado> findByStatus(int status, Pageable pageable);

    /**
     * Retorna chamados em um estado específico e em um período específico
     */
    @Query("select c from chamado c where c.status = ?1 and c.dataAbertura between ?2 and ?3")
    Page<Chamado> findByStatusAndDataAbertura(int status, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);

    /**
     * Retorna chamados em um estado específico e mais recentes que a data especificada
     */
    Page<Chamado> findByStatusAndDataAberturaGreaterThanEqual(int status, LocalDateTime data, Pageable pageable);

    /**
     * Retorna chamados em um estado específico e mais antigos que a data especificada
     */
    Page<Chamado> findByStatusAndDataAberturaLessThanEqual(int status, LocalDateTime data, Pageable pageable);
}
