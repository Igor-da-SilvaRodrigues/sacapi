package rj.cefet.sacapi.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.TipoChamado;

import java.util.List;

public interface RepositorioDeTipoChamado extends JpaRepository<TipoChamado, String> {
    Page<TipoChamado> findByArquivado(boolean arquivado, Pageable pageable);
}
