package rj.cefet.sacapi.servico;

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
    private RepositorioDeMotivo repositorioDeMotivo;

    public ServicoDeTipoChamado(RepositorioDeTipoChamado repoTipoChamado, RepositorioDeMotivo repositorioDeMotivo) {
        this.repoTipoChamado = repoTipoChamado;
        this.repositorioDeMotivo = repositorioDeMotivo;
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
}
