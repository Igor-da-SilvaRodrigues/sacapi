package rj.cefet.sacapi.controlador;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.ComentarioGetByChamadoDto;
import rj.cefet.sacapi.dto.ComentarioPostDto;
import rj.cefet.sacapi.dto.ComentarioPostResponseDto;
import rj.cefet.sacapi.enums.Status;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Comentario;
import rj.cefet.sacapi.modelo.Usuario;
import rj.cefet.sacapi.servico.ServicoDeChamado;
import rj.cefet.sacapi.servico.ServicoDeComentario;
import rj.cefet.sacapi.servico.ServicoDeUsuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sac/api/comentario")
public class ControladorDeComentario {
    private ServicoDeChamado servicoDeChamado;
    private ServicoDeUsuario servicoDeUsuario;
    private ServicoDeComentario servicoDeComentario;

    public ControladorDeComentario(ServicoDeChamado servicoDeChamado, ServicoDeUsuario servicoDeUsuario, ServicoDeComentario servicoDeComentario) {
        this.servicoDeChamado = servicoDeChamado;
        this.servicoDeUsuario = servicoDeUsuario;
        this.servicoDeComentario = servicoDeComentario;
    }

    @PostMapping
    ResponseEntity<ComentarioPostResponseDto> enviarComentario(@RequestBody @Valid ComentarioPostDto comentarioPostDto){
        Optional<Usuario> usuarioOptional = servicoDeUsuario.findById(comentarioPostDto.matriculaUsuario());
        Chamado chamado = servicoDeChamado.findById(comentarioPostDto.protocoloChamado());
        if (chamado.getStatus() != Status.RETORNADO.value) return ResponseEntity.badRequest().build();
        if (usuarioOptional.isEmpty()) return ResponseEntity.notFound().build();

        Comentario comentario = new Comentario();
        comentario.setChamado(chamado);
        comentario.setDataEnvio(LocalDateTime.now());
        comentario.setMensagem(comentarioPostDto.mensagem());
        comentario.setUsuario(usuarioOptional.get());

        return ResponseEntity.ok().body(ComentarioPostResponseDto.fromComentario(servicoDeComentario.salvarComentario(comentario)));
    }

    @GetMapping("/chamado/{idChamado}")
    ResponseEntity<List<ComentarioGetByChamadoDto>> getComentarioByChamado(@PathVariable @NotBlank String idChamado, Pageable pageable){
        Chamado chamado = servicoDeChamado.findById(idChamado);
        return ResponseEntity.ok().body(servicoDeComentario.findByChamado(chamado, pageable).map(ComentarioGetByChamadoDto::fromComentario).toList());
    }
}
