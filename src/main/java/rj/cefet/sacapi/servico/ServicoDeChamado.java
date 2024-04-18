package rj.cefet.sacapi.servico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Discente;
import rj.cefet.sacapi.repositorio.RepositorioDeChamado;
import rj.cefet.sacapi.repositorio.RepositorioDeDiscente;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoDeChamado {
    private RepositorioDeChamado repositorioDeChamado;
    private RepositorioDeDiscente repositorioDeDiscente;

    public ServicoDeChamado(RepositorioDeChamado repositorioDeChamado, RepositorioDeDiscente repositorioDeDiscente) {
        this.repositorioDeChamado = repositorioDeChamado;
        this.repositorioDeDiscente = repositorioDeDiscente;
    }

    public Chamado salvar(Chamado chamado){
        return repositorioDeChamado.save(chamado);
    }

    public List<Chamado> findAllChamados(){
        return repositorioDeChamado.findAll();
    }
    public Optional<Chamado> findById(String protocolo){
        return repositorioDeChamado.findById(protocolo);
    }

    /**
     * Retorna todos os chamados de um usuário
     */
    public List<Chamado> findByMatriculaDeDiscente(String matricula){
        Optional<Discente> discenteOptional = repositorioDeDiscente.findById(matricula);
        //retornar 404 se não encontrar discente
        if (discenteOptional.isEmpty()) throw new EntityNotFoundException("Usuario de id %s não encontrado".formatted(matricula));
        return discenteOptional.get().getChamados();
    }


}
