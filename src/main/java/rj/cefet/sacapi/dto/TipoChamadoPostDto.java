package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;

import java.util.Set;
import java.util.stream.Collectors;

public record TipoChamadoPostDto(String tipo, Set<String> motivos, Integer prioridade) {
    /**
     * Converte este dto em um TipoChamado
     * @return
     */
    public TipoChamado toTipoChamado(){
        var tipoChamado = new TipoChamado();
        tipoChamado.setArquivado(false);
        tipoChamado.setTipo(tipo());
        tipoChamado.setPrioridade(prioridade());
        //Converter lista de string em um set de motivos, já contendo os relacionamentos.
        Set<Motivo> motivoList = motivos.stream().map(s -> new Motivo(s, tipoChamado)).collect(Collectors.toSet());
        tipoChamado.setMotivoSet(motivoList);
        return tipoChamado;
    }
}
