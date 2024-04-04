package rj.cefet.sacapi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rj.cefet.sacapi.dto.UsuarioGetDto;
import rj.cefet.sacapi.servico.ServicoDeUsuario;

import java.util.List;

@RestController
@CrossOrigin(origins = "", maxAge = 3600)
@RequestMapping("/usuario")
public class ControladorDeUsuario {

    private ServicoDeUsuario servicoDeUsuario;

    public ControladorDeUsuario(ServicoDeUsuario servicoDeUsuario) {
        this.servicoDeUsuario = servicoDeUsuario;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioGetDto>> getAllUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(
                servicoDeUsuario.findAllUsuario().stream().map(UsuarioGetDto::fromUsuario).toList()
        );
    }
}
