package rj.cefet.sacapi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.UsuarioGetDto;
import rj.cefet.sacapi.modelo.Usuario;
import rj.cefet.sacapi.servico.ServicoDeUsuario;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioGetDto> getById(@PathVariable String idUsuario){
        Optional<Usuario> usuarioOptional = servicoDeUsuario.findById(idUsuario);
        return usuarioOptional.map(usuario -> ResponseEntity.ok().body(UsuarioGetDto.fromUsuario(usuario))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
