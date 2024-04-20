package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Chamado;

public record ChamadoPostResposeDto(
        String protocolo,
        Integer status,
        String parecer,
        String setor,
        String tipoChamado,
        String motivo,
        String justificativa,
        String dataAbertura,
        String matriculaAluno,
        String nomeAluno
) {
    public static ChamadoPostResposeDto fromChamado(Chamado chamado){
        return new ChamadoPostResposeDto(
                chamado.getProtocolo(),
                chamado.getStatus(),
                chamado.getParecer(),
                chamado.getSetor() != null ? chamado.getSetor().getSetor(): null, //setor pode ser nulo. Sempre é nulo quando o chamado é aberto
                chamado.getTipoChamado().getTipo(),
                chamado.getMotivo().getMotivo(),
                chamado.getJustificativa(),
                chamado.getDataAbertura().toLocalDate().toString(),
                chamado.getDiscente().getMatricula(),
                chamado.getDiscente().getNome()
        );
    }
}
