package rj.cefet.sacapi.servico;

import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Comentario;
import rj.cefet.sacapi.repositorio.RepositorioDeComentario;

import java.util.List;

@Service
public class ServicoDeComentario {
    private RepositorioDeComentario repositorioDeComentario;

    public ServicoDeComentario(RepositorioDeComentario repositorioDeComentario) {
        this.repositorioDeComentario = repositorioDeComentario;
    }

    public Comentario salvarComentario(Comentario comentario){
        return repositorioDeComentario.save(comentario);
    }

    public List<Comentario> findByChamado(Chamado chamado){
        return this.repositorioDeComentario.findByChamadoOrderByDataEnvioDesc(chamado);
    }
}
