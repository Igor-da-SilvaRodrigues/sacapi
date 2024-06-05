package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Historico;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public record ChamadoGetDto(
        String protocolo,
        LocalDate dataAbertura,
        LocalDate dataFechamento,
        Integer status,
        Integer prioridade,
        String aluno,
        String matricula,
        String dataMod
) {
    public static ChamadoGetDto fromChamado(Chamado chamado) {
        return new ChamadoGetDto(
                chamado.getProtocolo(),
                chamado.getDataAbertura().toLocalDate(),
                chamado.getDataFechamento() != null ? chamado.getDataFechamento().toLocalDate() : null,
                chamado.getStatus(),
                chamado.getTipoChamado().getPrioridade(),//cheirinho de NullPointerException,
                chamado.getDiscente().getNome(),
                chamado.getDiscente().getMatricula(),
                chamado.getDataMod() != null ? chamado.getDataMod().toString() : null
        );
    }
}
