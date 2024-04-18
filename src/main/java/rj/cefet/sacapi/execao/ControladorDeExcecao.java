package rj.cefet.sacapi.execao;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.security.access.AccessDeniedException;
import javax.naming.AuthenticationException;

@ControllerAdvice
public class ControladorDeExcecao {
    //erro de autenticação
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Matrícula ou senha incorretas!");
    }

    //mensagem http mal formada
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        ex.printStackTrace();
        return ResponseEntity.badRequest().build();
    }

    //erro de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ex.printStackTrace();
        return ResponseEntity.badRequest().build();
    }

    //erro de validação
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleHandlerMethodValidationException(HandlerMethodValidationException ex){
        ex.printStackTrace();
        return ResponseEntity.badRequest().build();
    }
    //endpoint inexistente
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException ex){
        ex.printStackTrace();
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
        ex.printStackTrace();
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex){
        ex.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex){
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
