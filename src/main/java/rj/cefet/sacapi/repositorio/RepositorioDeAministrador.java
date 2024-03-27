package rj.cefet.sacapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import rj.cefet.sacapi.modelo.Administrador;

import java.util.UUID;

public interface RepositorioDeAministrador extends JpaRepository<Administrador, UUID> {
}
