package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity(name = "tipo_chamado")
@Table(name = "tipo_chamado")
public class TipoChamado implements Serializable {
    @Id
    private String tipo;
    private int prioridade;

    private boolean arquivado;

    @OneToMany(mappedBy = "tipoChamado", cascade = CascadeType.ALL)//cascade pois "motivo" não tem significado ou uso fora de um tipo.
    private Set<Motivo> motivoSet;
    public TipoChamado() {
    }

    public boolean isArquivado() {
        return arquivado;
    }

    public void setArquivado(boolean arquivado) {
        this.arquivado = arquivado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public Set<Motivo> getMotivoSet() {
        return motivoSet;
    }

    public void setMotivoSet(Set<Motivo> motivoSet) {
        this.motivoSet = motivoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoChamado that = (TipoChamado) o;
        return getPrioridade() == that.getPrioridade() && Objects.equals(getTipo(), that.getTipo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTipo(), getPrioridade());
    }
}
