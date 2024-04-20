package rj.cefet.sacapi.dto;

import jakarta.validation.constraints.NotBlank;
import rj.cefet.sacapi.modelo.Comentario;

public record ComentarioPostDto(
        @NotBlank
        String mensagem,
        @NotBlank
        String matriculaUsuario,
        @NotBlank
        String protocoloChamado
) {
}
