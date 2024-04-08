package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;

import java.util.List;

public record TipoChamadoGetDto(String tipo, Integer prioridade, List<String> motivos, Boolean arquivado) {
    public static TipoChamadoGetDto fromTipoChamado(TipoChamado tipoChamado){
        return new TipoChamadoGetDto(
                tipoChamado.getTipo(),
                tipoChamado.getPrioridade(),
                tipoChamado.getMotivoList().stream().map(Motivo::asString).toList(),
                tipoChamado.isArquivado()
        );
    }
}
