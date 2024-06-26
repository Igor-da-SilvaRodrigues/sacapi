package rj.cefet.sacapi.seguranca;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rj.cefet.sacapi.modelo.Administrador;
import rj.cefet.sacapi.seguranca.servico.ServicoDeAutenticacao;
import rj.cefet.sacapi.seguranca.servico.ServicoDeTokens;

import java.io.IOException;
import java.util.Objects;

@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {

    private ServicoDeTokens servicoDeToken;
    private ServicoDeAutenticacao servicoDeAutenticacao;

    public FiltroDeSeguranca(ServicoDeTokens servicoDeToken, ServicoDeAutenticacao servicoDeAutenticacao) {
        this.servicoDeToken = servicoDeToken;
        this.servicoDeAutenticacao = servicoDeAutenticacao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);

        if(Objects.equals(token, "dev")){//always permit token 'dev'. For development purposes only.
            var admin = new Administrador();
            admin.setMatricula("admin");
            admin.setSenha("admin");
            var authentication = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else if (token != null){
            var matricula = servicoDeToken.validarToken(token);//
            UserDetails userDetails = servicoDeAutenticacao.loadUserByUsername(matricula);

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null){return null;}
        return authHeader.replace("Bearer ", "");
    }
}
