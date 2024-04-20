package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Chamado;

import java.time.LocalDate;

public record ChamadoGetDto(
        String protocolo,
        LocalDate dataAbertura,
        Integer status,
        Integer prioridade
) {
    public static ChamadoGetDto fromChamado(Chamado chamado) {
        return new ChamadoGetDto(
                chamado.getProtocolo(),
                chamado.getDataAbertura().toLocalDate(),
                chamado.getStatus(),
                chamado.getTipoChamado().getPrioridade()//cheirinho de NullPointerException
        );
    }
}
