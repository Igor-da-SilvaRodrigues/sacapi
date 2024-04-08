package rj.cefet.sacapi.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Chave composta de Motivo. Permitindo motivos de mesmo nome entre tipos diferentes, mas impedindo motivos de mesmo nome no mesmo tipo
 */
public class MotivoId implements Serializable {
    private String motivo;
    private TipoChamado tipoChamado;

    public MotivoId() {
    }

    public MotivoId(String motivo, TipoChamado tipoChamado) {
        this.motivo = motivo;
        this.tipoChamado = tipoChamado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotivoId motivoId = (MotivoId) o;
        return Objects.equals(motivo, motivoId.motivo) && Objects.equals(tipoChamado, motivoId.tipoChamado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motivo, tipoChamado);
    }
}
