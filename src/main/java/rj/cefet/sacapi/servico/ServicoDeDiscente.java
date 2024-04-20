package rj.cefet.sacapi.servico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Discente;
import rj.cefet.sacapi.repositorio.RepositorioDeDiscente;

import java.util.Optional;

@Service
public class ServicoDeDiscente {
    private RepositorioDeDiscente repositorioDeDiscente;

    public ServicoDeDiscente(RepositorioDeDiscente repositorioDeDiscente) {
        this.repositorioDeDiscente = repositorioDeDiscente;
    }

    /**
     * Resgata um discente baseado em sua matricula. Se o discente não for encontrado, aciona uma resposta 404.
     */
    public Discente findById(String matricula){
        Optional<Discente> discenteOptional = repositorioDeDiscente.findById(matricula);
        if (discenteOptional.isEmpty()) throw new EntityNotFoundException("Não foi possível encontrar discente de matriula %s".formatted(matricula));
        return discenteOptional.get();
    }
}
