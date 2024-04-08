package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Setor;

public record SetorPostDto(String setor, String email) {
    public Setor toSetor(){
        Setor setor = new Setor();
        setor.setSetor(setor());
        setor.setEmail(email());
        return setor;
    }
}
