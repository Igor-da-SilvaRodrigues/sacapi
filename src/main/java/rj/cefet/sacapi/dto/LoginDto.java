package rj.cefet.sacapi.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank String matricula, @NotBlank String senha) {
}
