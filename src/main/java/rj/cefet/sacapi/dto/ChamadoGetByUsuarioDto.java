package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Chamado;

public record ChamadoGetByUsuarioDto(Inicial inicial, Terminal terminal) {
    public static ChamadoGetByUsuarioDto fromChamado(Chamado chamado){
        return new ChamadoGetByUsuarioDto(
                new Inicial(
                        chamado.getProtocolo(),
                        chamado.getTipoChamado().getTipo(),
                        chamado.getMotivo().getMotivo(),
                        chamado.getJustificativa(),
                        chamado.getDataAbertura().toLocalDate().toString()
                ),
                new Terminal(
                        chamado.getParecer(),
                        chamado.getDataFechamento() != null ? chamado.getDataFechamento().toLocalDate().toString() : null
                )
        );
    }

    private record Inicial(
            String protocolo,
            String tipoChamado,
            String motivo,
            String justificativa,
            String dataAbertura
    ){}
    private record Terminal(
            String parecer,
            String dataFechamento
    ){}
}
