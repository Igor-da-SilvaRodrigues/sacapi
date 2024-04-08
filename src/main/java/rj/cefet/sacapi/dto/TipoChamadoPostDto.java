package rj.cefet.sacapi.dto;

import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;

import java.util.List;

public record TipoChamadoPostDto(String tipo, List<String> motivos, Integer prioridade) {
    public TipoChamado toTipoChamado(){
        var tipoChamado = new TipoChamado();
        tipoChamado.setArquivado(false);
        tipoChamado.setTipo(tipo());
        tipoChamado.setPrioridade(prioridade());
        return tipoChamado;
    }
}
