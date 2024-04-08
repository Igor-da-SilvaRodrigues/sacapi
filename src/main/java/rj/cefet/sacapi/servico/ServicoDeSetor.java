package rj.cefet.sacapi.servico;

import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Setor;
import rj.cefet.sacapi.repositorio.RepositorioDeSetor;

import java.util.List;

@Service
public class ServicoDeSetor {
    private RepositorioDeSetor repositorioDeSetor;

    public ServicoDeSetor(RepositorioDeSetor repositorioDeSetor) {
        this.repositorioDeSetor = repositorioDeSetor;
    }

    public List<Setor> findAllSetores(){
        return repositorioDeSetor.findAll();
    }

    public Setor salvar(Setor setor){
        return repositorioDeSetor.save(setor);
    }

}
