package rj.cefet.sacapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import rj.cefet.sacapi.modelo.Administrador;
import rj.cefet.sacapi.modelo.Usuario;

public record UsuarioGetDto(
        @Schema(example = "abcde")
        String matricula,
        @Schema(example = "fulano")
        String nome,
        @Schema(example = "false")
        Boolean usuarioAdm) {

    public static UsuarioGetDto fromUsuario(Usuario usuario) {
        return new UsuarioGetDto(
                usuario.getMatricula(),
                usuario.getNome(),
                usuario instanceof Administrador);//usuário é admin se for instancia de admin
    }
}
