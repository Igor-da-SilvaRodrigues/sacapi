package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "chamado")
@Table(name = "chamado")
public class Chamado {
    @Id
    private String protocolo;
    @JoinColumn(name = "tipo_chamado")
    @ManyToOne
    private TipoChamado tipoChamado;
    @ManyToOne
    private Motivo motivo;
    private String justificativa;
    private int status;
    private String parecer;
    @ManyToOne
    private Setor setor;
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;
    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;
    @ManyToOne
    private Discente discente;
    @OneToMany(mappedBy = "chamado")
    private List<Historico> historicos;
    public Chamado() {
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public TipoChamado getTipoChamado() {
        return tipoChamado;
    }

    public void setTipoChamado(TipoChamado tipoChamado) {
        this.tipoChamado = tipoChamado;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    /**
     * Esse método <b>DEVE</b> ser chamado após {@link #setTipoChamado(TipoChamado)}
     * @param motivo o motivo selecionado
     * @throws IllegalStateException se for chamado antes de {@link #setTipoChamado(TipoChamado)}
     * @throws IllegalArgumentException se o motivo não for admitido pelo tipo do chamado
     */
    public void setMotivo(Motivo motivo) {
        if(this.tipoChamado == null){
            throw new IllegalStateException("O chamado não possui tipo");
        }
        if (!this.tipoChamado.getMotivoList().contains(motivo)){
            throw new IllegalArgumentException("O tipo deste chamado não admite o motivo {%s}".formatted(motivo.toString()));
        }
        this.motivo = motivo;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
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

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public List<Historico> getHistoricos() {
        return historicos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return getStatus() == chamado.getStatus() && Objects.equals(getProtocolo(), chamado.getProtocolo()) && Objects.equals(getJustificativa(), chamado.getJustificativa()) && Objects.equals(getParecer(), chamado.getParecer()) && Objects.equals(getDataAbertura(), chamado.getDataAbertura()) && Objects.equals(getDataFechamento(), chamado.getDataFechamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProtocolo(), getJustificativa(), getStatus(), getParecer(), getDataAbertura(), getDataFechamento());
    }
}
