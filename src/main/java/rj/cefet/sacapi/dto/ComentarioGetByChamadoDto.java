package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Comentario;

public record ComentarioGetByChamadoDto(
        String id,
        String mensagem,
        String dataEnvio,
        String usuarioNome,
        String usuarioMatricula,
        String chamadoProtocolo
) {
    public static ComentarioGetByChamadoDto fromComentario(Comentario comentario){
        return new ComentarioGetByChamadoDto(
                comentario.getId().toString(),
                comentario.getMensagem(),
                comentario.getDataEnvio().toString(),
                comentario.getUsuario().getNome(),
                comentario.getUsuario().getMatricula(),
                comentario.getChamado().getProtocolo()
        );
    }
}
