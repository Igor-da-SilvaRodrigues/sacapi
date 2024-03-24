package rj.cefet.sacapi.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity(name = "discente")
@Table(name = "discente")
public class Discente extends Usuario{
    @OneToMany(mappedBy = "discente")
    private List<Chamado> chamados;

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public void addChamado(Chamado chamado){
        this.chamados.add(chamado);
    }
    public void addChamados(List<Chamado> chamados){
        this.chamados.addAll(chamados);
    }

}
