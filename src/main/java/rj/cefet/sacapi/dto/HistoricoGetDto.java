package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Historico;

public record HistoricoGetDto(
        Integer status,
        String parecer,
        String dataMod,
        String setor
) {
    public static HistoricoGetDto fromHistorico(Historico historico){
        return new HistoricoGetDto(
                historico.getStatus(),
                historico.getParecer(),
                historico.getDataMod().toLocalDate().toString(),
                historico.getSetor() != null ? historico.getSetor().getSetor() : null
        );
    }
}
