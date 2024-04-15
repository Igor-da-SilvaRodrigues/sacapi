package rj.cefet.sacapi.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDiscenteDto(
        @NotBlank
        String cep,
        @NotBlank
        String email,
        @NotBlank
        String endereco,
        @NotBlank
        String nome,
        @NotBlank
        String senha,
        @NotBlank
        String telefone
) {
}
