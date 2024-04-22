package rj.cefet.sacapi.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Comentario;
import rj.cefet.sacapi.repositorio.RepositorioDeComentario;

@Service
public class ServicoDeComentario {
    private RepositorioDeComentario repositorioDeComentario;

    public ServicoDeComentario(RepositorioDeComentario repositorioDeComentario) {
        this.repositorioDeComentario = repositorioDeComentario;
    }

    public Comentario salvarComentario(Comentario comentario){
        return repositorioDeComentario.save(comentario);
    }

    public Page<Comentario> findByChamado(Chamado chamado, Pageable pageable){
        return this.repositorioDeComentario.findByChamado(chamado, pageable);
    }
}
