package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

@Entity(name = "administrador")
@Table(name = "administrador")
public class Administrador extends Usuario{
    @OneToOne
    private Setor setor;

    public Administrador() {
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

}
