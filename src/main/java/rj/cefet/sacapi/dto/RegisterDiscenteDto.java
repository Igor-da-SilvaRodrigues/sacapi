package rj.cefet.sacapi.dto;

public record RegisterDiscenteDto(
        String cep,
        String email,
        String endereco,
        String nome,
        String senha,
        String telefone
) {
}
