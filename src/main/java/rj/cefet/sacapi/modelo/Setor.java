package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "setor")
@Table(name = "setor")
public class Setor {
    @Id
    private String setor;
    private String email;
    @OneToMany(mappedBy = "setor")
    private List<Chamado> chamados;
    @OneToOne(mappedBy = "setor")
    private Administrador administrador;
    public Setor() {
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }



    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setor setor1 = (Setor) o;
        return Objects.equals(getSetor(), setor1.getSetor()) && Objects.equals(getEmail(), setor1.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSetor(), getEmail());
    }
}
