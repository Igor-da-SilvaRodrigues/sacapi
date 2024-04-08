package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record TipoChamadoGetDto(String tipo, Integer prioridade, Set<String> motivos, Boolean arquivado) {
    /**
     * Converte um TipoChamado em um dto
     */
    public static TipoChamadoGetDto fromTipoChamado(TipoChamado tipoChamado){
        return new TipoChamadoGetDto(
                tipoChamado.getTipo(),
                tipoChamado.getPrioridade(),
                tipoChamado.getMotivoSet().stream().map(Motivo::asString).collect(Collectors.toSet()),
                tipoChamado.isArquivado()
        );
    }
}
