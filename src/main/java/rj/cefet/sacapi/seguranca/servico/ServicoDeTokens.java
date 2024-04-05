package rj.cefet.sacapi.seguranca.servico;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rj.cefet.sacapi.modelo.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ServicoDeTokens {
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token de sessão único para o usuário
     * @param usuario
     * @return
     */
    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("sacapi")
                    .withSubject(usuario.getMatricula())
                    .withExpiresAt(gerarValidade())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token de sessão", e);
        }
    }

    /**
     * Valida o token de sessão e recupera a matrícula do usuário
     * @param token
     * @return
     */
    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("sacapi")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro ao validar token de sessão", e);
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    /**
     * Determina a data de validade do token de sessão.
     */
    private Instant gerarValidade(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
