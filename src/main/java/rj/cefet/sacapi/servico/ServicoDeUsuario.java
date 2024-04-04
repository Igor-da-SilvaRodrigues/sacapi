package rj.cefet.sacapi.servico;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Discente;
import rj.cefet.sacapi.modelo.Usuario;
import rj.cefet.sacapi.repositorio.RepositorioDeUsuario;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoDeUsuario {

    private RepositorioDeUsuario repositorioDeUsuario;

    public ServicoDeUsuario(RepositorioDeUsuario repositorioDeUsuario) {
        this.repositorioDeUsuario = repositorioDeUsuario;
    }

    public List<Usuario> findAllUsuario(){
        return repositorioDeUsuario.findAll();
    }

    public Optional<Usuario> findById(String id){
        return repositorioDeUsuario.findById(id);
    }


    public void salvar(Discente discente) {
        this.repositorioDeUsuario.save(discente);
    }
}
