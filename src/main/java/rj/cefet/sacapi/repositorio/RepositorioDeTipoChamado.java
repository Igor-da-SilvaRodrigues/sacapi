package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.TipoChamado;

public interface RepositorioDeTipoChamado extends JpaRepository<TipoChamado, String> {
}
