package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Setor;

public interface RepositorioDeSetor extends JpaRepository<Setor, String> {
}
