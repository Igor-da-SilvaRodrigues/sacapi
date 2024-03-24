package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "tipo_chamado")
@Table(name = "tipo_chamado")
public class TipoChamado {
    @Id
    private String tipo;
    private int prioridade;

    private boolean arquivado;

    @OneToMany(mappedBy = "tipoChamado")
    private List<Motivo> motivoList;
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

    public List<Motivo> getMotivoList() {
        return motivoList;
    }

    public void setMotivoList(List<Motivo> motivoList) {
        this.motivoList = motivoList;
    }

    public void addMotivo(Motivo motivo){
        this.motivoList.add(motivo);
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
