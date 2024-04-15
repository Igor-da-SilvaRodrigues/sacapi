package rj.cefet.sacapi.seguranca.servico;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.repositorio.RepositorioDeUsuario;

@Service
public class ServicoDeAutenticacao implements UserDetailsService {
    private RepositorioDeUsuario repoUsuario;

    public ServicoDeAutenticacao(RepositorioDeUsuario repoUsuario) {
        this.repoUsuario = repoUsuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails retorno = repoUsuario.findByMatricula(username);
        if (retorno == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return retorno;
    }
}
