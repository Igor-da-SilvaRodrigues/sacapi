package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Administrador;
import rj.cefet.sacapi.modelo.Usuario;

public record UsuarioGetDto(String matricula, String nome, Boolean usuarioAdm) {

    public static UsuarioGetDto fromUsuario(Usuario usuario) {
        return new UsuarioGetDto(
                usuario.getMatricula(),
                usuario.getNome(),
                usuario instanceof Administrador);//usuário é admin se for instancia de admin
    }
}
