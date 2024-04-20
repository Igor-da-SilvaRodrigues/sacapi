package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "historico")
@Table(name = "historico")
public class Historico {
    @Id
    private UUID id;
    @ManyToOne
    private Chamado chamado;
    @ManyToOne
    private Motivo motivo;
    @ManyToOne
    private TipoChamado tipoChamado;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataMod;
    private int status;
    private String parecer;
    @ManyToOne
    private Setor setor;

    public Historico() {
    }

    /**
     * Cria um histórico com base em um chamado e os relaciona.
     * A data e hora atual é usada como data de modificação.
     * @param chamado O chamado que será utilizado para criar o histórico.
     */
    public Historico(Chamado chamado){
        this.id = UUID.randomUUID();
        this.chamado = chamado;
        this.motivo = chamado.getMotivo();
        this.tipoChamado = chamado.getTipoChamado();
        this.setor = chamado.getSetor();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataMod = LocalDateTime.now();
        this.status = chamado.getStatus();
        this.parecer = chamado.getParecer();
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public TipoChamado getTipoChamado() {
        return tipoChamado;
    }

    public void setTipoChamado(TipoChamado tipoChamado) {
        this.tipoChamado = tipoChamado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataMod() {
        return dataMod;
    }

    public void setDataMod(LocalDateTime dataMod) {
        this.dataMod = dataMod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historico historico = (Historico) o;
        return getStatus() == historico.getStatus() && Objects.equals(getId(), historico.getId()) && Objects.equals(getChamado(), historico.getChamado()) && Objects.equals(getMotivo(), historico.getMotivo()) && Objects.equals(getTipoChamado(), historico.getTipoChamado()) && Objects.equals(getDataAbertura(), historico.getDataAbertura()) && Objects.equals(getDataMod(), historico.getDataMod()) && Objects.equals(getParecer(), historico.getParecer()) && Objects.equals(getSetor(), historico.getSetor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getChamado(), getMotivo(), getTipoChamado(), getDataAbertura(), getDataMod(), getStatus(), getParecer(), getSetor());
    }
}
