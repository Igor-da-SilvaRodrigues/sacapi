package rj.cefet.sacapi.servico;

import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;
import rj.cefet.sacapi.repositorio.RepositorioDeMotivo;
import rj.cefet.sacapi.repositorio.RepositorioDeTipoChamado;

import java.util.Collections;
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

    public TipoChamado salvar(TipoChamado tipoChamado, List<String> motivos){
        //primeiro salvamos o tipo de chamado sem relacionamentos
        TipoChamado salvo = repoTipoChamado.save(tipoChamado);
        //criamos os motivos, já relacionados ao novo tipo de chamado
        List<Motivo> motivosSalvos = repositorioDeMotivo.saveAll(
                motivos.stream().map(s -> new Motivo(s, salvo)).toList()
        );

        //agora nosso objeto salvo está desatualizado, inserimos a lista de motivos criada, no nosso objeto de tipo
        salvo.setMotivoList(motivosSalvos);
        //Poderíamos salvar o tipo de chamado para atualizar a tabela caso necessário,
        //ou resgatar a entidade diretamente do banco de dados, mas neste caso não será necessário.
        //Apenas retornamos o objeto atualizado.
        return salvo;
    }


    /**
     * Arquiva um tipo de chamado
     * @param id
     * @return Um Optional contendo o tipo chamado arquivado. O optional é vazio caso o tipo de chamado não exista.
     */
    public Optional<TipoChamado> arquivarChamadoById(String id){
        Optional<TipoChamado> tipoChamadoOptional = repoTipoChamado.findById(id);//acha o tipo
        if (tipoChamadoOptional.isEmpty()){return tipoChamadoOptional;}//retorna optional vazio caso não exista
        TipoChamado tipoChamado = tipoChamadoOptional.get();
        tipoChamado.setArquivado(true);
        TipoChamado tipoChamadoArquivado = repoTipoChamado.save(tipoChamado);//arquiva o tipo
        return Optional.of(tipoChamadoArquivado);
    }

    public List<TipoChamado> findTipoChamadoByArquivado(boolean arquivado){
        return repoTipoChamado.findByArquivado(arquivado);
    }

    public List<TipoChamado> findAllTipoChamado() {
        return repoTipoChamado.findAll();
    }
}
