package rj.cefet.sacapi.dto;

import jakarta.validation.constraints.NotBlank;
import rj.cefet.sacapi.modelo.Setor;

public record SetorPostDto(@NotBlank String setor, @NotBlank String email) {
    public Setor toSetor(){
        Setor setor = new Setor();
        setor.setSetor(setor());
        setor.setEmail(email());
        return setor;
    }
}
