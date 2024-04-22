package rj.cefet.sacapi.servico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private ServicoDeDiscente servicoDeDiscente;

    public ServicoDeChamado(RepositorioDeChamado repositorioDeChamado, ServicoDeDiscente servicoDeDiscente) {
        this.repositorioDeChamado = repositorioDeChamado;
        this.servicoDeDiscente = servicoDeDiscente;
    }

    public Chamado salvar(Chamado chamado){
        return repositorioDeChamado.save(chamado);
    }

    public Page<Chamado> findAllChamados(Pageable pageable){
        return repositorioDeChamado.findAll(pageable);
    }
    public Chamado findById(String protocolo){
        Optional<Chamado> chamadoOptional = repositorioDeChamado.findById(protocolo);
        if (chamadoOptional.isEmpty()) throw new EntityNotFoundException("Chamado de protocolo '%s' não encontrado".formatted(protocolo));
        return chamadoOptional.get();
    }

    /**
     * Retorna todos os chamados de um usuário
     */
    public Page<Chamado> findByMatriculaDeDiscente(String matricula, Pageable pageable){
        Discente discente = servicoDeDiscente.findById(matricula);
        return repositorioDeChamado.findByDiscente(discente, pageable);
    }
}
