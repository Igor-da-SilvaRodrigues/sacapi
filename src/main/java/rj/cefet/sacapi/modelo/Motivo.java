package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "motivo")
@Table(name = "motivo")
public class Motivo {
    @Id
    private String motivo;
    @ManyToOne
    @JoinColumn(name = "tipo_chamado")
    private TipoChamado tipoChamado;

    public Motivo() {
    }

    public Motivo(String motivo, TipoChamado tipoChamado){
        this.motivo = motivo;
        this.tipoChamado = tipoChamado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public TipoChamado getTipoChamado() {
        return tipoChamado;
    }

    public void setTipoChamado(TipoChamado tipoChamado) {
        this.tipoChamado = tipoChamado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motivo motivo1 = (Motivo) o;
        return Objects.equals(getMotivo(), motivo1.getMotivo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMotivo());
    }

    public static Motivo fromString(String motivo) {
        Motivo motivo1 = new Motivo();
        motivo1.setMotivo(motivo);
        return motivo1;
    }

    public String asString() {
        return this.motivo;
    }
}
