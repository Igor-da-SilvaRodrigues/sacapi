package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Setor;

public record SetorGetDto(String setor, String email) {
    public static SetorGetDto fromSetor(Setor setor){
        return new SetorGetDto(setor.getSetor(), setor.getEmail());
    }
}
