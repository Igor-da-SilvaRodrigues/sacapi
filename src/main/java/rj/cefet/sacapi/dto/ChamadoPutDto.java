package rj.cefet.sacapi.dto;

import jakarta.validation.constraints.NotBlank;

public record ChamadoPutDto(
        @NotBlank
        String idUsuario,
        @NotBlank
        String protocolo,
        Integer status,
        String parecer,
        String idSetor
) {
}
