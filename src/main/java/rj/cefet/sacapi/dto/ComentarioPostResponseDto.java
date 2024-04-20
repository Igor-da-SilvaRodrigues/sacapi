package rj.cefet.sacapi.dto;

import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import rj.cefet.sacapi.modelo.Comentario;

import java.time.format.DateTimeFormatter;

public record ComentarioPostResponseDto(
        String id,
        String mensagem,
        String dataEnvio,
        String usuarioNome,
        String usuarioMatricula,
        String chamadoProtocolo
) {
    public static ComentarioPostResponseDto fromComentario(Comentario comentario){
        return new ComentarioPostResponseDto(
                comentario.getId().toString(),
                comentario.getMensagem(),
                comentario.getDataEnvio().toString(),
                comentario.getUsuario().getNome(),
                comentario.getUsuario().getMatricula(),
                comentario.getChamado().getProtocolo()
        );
    }
}
