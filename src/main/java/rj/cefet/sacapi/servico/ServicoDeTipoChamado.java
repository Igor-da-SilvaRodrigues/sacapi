package rj.cefet.sacapi.servico;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;
import rj.cefet.sacapi.repositorio.RepositorioDeMotivo;
import rj.cefet.sacapi.repositorio.RepositorioDeTipoChamado;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoDeTipoChamado {
    private RepositorioDeTipoChamado repoTipoChamado;

    public ServicoDeTipoChamado(RepositorioDeTipoChamado repoTipoChamado) {
        this.repoTipoChamado = repoTipoChamado;
    }

    /**
     * Salva um TipoChamado juntamente com seus Motivos.
     * @param tipoChamado
     * @return O tipo persistido.
     */
    public TipoChamado salvar(TipoChamado tipoChamado){
        return repoTipoChamado.save(tipoChamado);
    }


    /**
     * Arquiva um tipo de chamado
     * @param id
     * @return Um Optional contendo o tipo chamado arquivado. O optional é vazio caso o tipo de chamado não exista.
     */
    public Optional<TipoChamado> arquivarChamadoById(String id){
        Optional<TipoChamado> tipoChamadoOptional = repoTipoChamado.findById(id);//acha o tipo
        if (tipoChamadoOptional.isEmpty()){return tipoChamadoOptional;}//retorna optional vazio caso não exista

        var tipoChamado = tipoChamadoOptional.get();
        tipoChamado.setArquivado(true);
        var tipoChamadoArquivado = repoTipoChamado.save(tipoChamado);//arquiva o tipo

        return Optional.of(tipoChamadoArquivado);
    }

    public List<TipoChamado> findTipoChamadoByArquivado(boolean arquivado){
        return repoTipoChamado.findByArquivado(arquivado);
    }

    public List<TipoChamado> findAllTipoChamado() {
        return repoTipoChamado.findAll();
    }

    /**
     * Resgata o tipo de chamado baseado no seu ID. Se ele não for encontrado, aciona uma resposta 404.
     */
    public TipoChamado findById(String id){
        Optional<TipoChamado> tipoChamadoOptional = repoTipoChamado.findById(id);
        if (tipoChamadoOptional.isEmpty()) throw new EntityNotFoundException("TipoChamado de id %s não econtrado.".formatted(id));

        return tipoChamadoOptional.get();
    }
}
