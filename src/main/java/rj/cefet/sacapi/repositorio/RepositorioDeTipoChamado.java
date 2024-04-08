package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.TipoChamado;

import java.util.List;

public interface RepositorioDeTipoChamado extends JpaRepository<TipoChamado, String> {
    List<TipoChamado> findByArquivado(boolean arquivado);
}
