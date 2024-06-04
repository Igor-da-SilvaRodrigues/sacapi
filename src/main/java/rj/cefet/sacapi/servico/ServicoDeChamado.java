package rj.cefet.sacapi.servico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Discente;
import rj.cefet.sacapi.repositorio.RepositorioDeChamado;
import rj.cefet.sacapi.repositorio.RepositorioDeDiscente;

import java.time.LocalDateTime;
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

    public Page<Chamado> findChamadoByStatus(int status, Pageable pageable){
        return repositorioDeChamado.findByStatus(status, pageable);
    }

    /**
     *
     * Retorna os chamados de determinado status, abertos em um determinado período.
     * <il>
     *     <li>Caso a data inicial não seja especificada, retorna todos os chamados mais antigos que a data final.</li>
     *     <li>Caso a data final não seja especificada, retorna todos os chamados mais recentes que a data inicial.</li>
     *     <li>Caso o período não seja especificado, retorna todos os chamados com o status especificado</li>
     *</il>
     *
     * @param status
     * @param dataInicio
     * @param dataFim
     * @param pageable
     * @return
     */
    public Page<Chamado> findChamadoByStatusAndDataAbertura(int status, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable){
        if(dataInicio == null && dataFim == null){
            return findChamadoByStatus(status, pageable);
        }

        if(dataInicio == null){
            return repositorioDeChamado.findByStatusAndDataAberturaLessThanEqual(status, dataFim, pageable);
        }

        if(dataFim == null){
            return repositorioDeChamado.findByStatusAndDataAberturaGreaterThanEqual(status, dataInicio, pageable);
        }

        return repositorioDeChamado.findByStatusAndDataAbertura(status, dataInicio, dataFim, pageable);
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
