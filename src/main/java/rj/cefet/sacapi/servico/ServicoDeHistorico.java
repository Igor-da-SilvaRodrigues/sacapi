package rj.cefet.sacapi.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Historico;
import rj.cefet.sacapi.repositorio.RepositorioDeHistorico;

import java.util.List;

@Service
public class ServicoDeHistorico {

    private RepositorioDeHistorico repositorioDeHistorico;

    public ServicoDeHistorico(RepositorioDeHistorico repositorioDeHistorico) {
        this.repositorioDeHistorico = repositorioDeHistorico;
    }

    public Historico criarHistoricoDeChamado(Chamado chamado){
        return repositorioDeHistorico.save(new Historico(chamado));
    }

    public Page<Historico> getHistoricosByChamado(Chamado chamado, Pageable pageable){
        return repositorioDeHistorico.findByChamado(chamado, pageable);
    }
}
