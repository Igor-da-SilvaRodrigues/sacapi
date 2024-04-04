package rj.cefet.sacapi.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity(name = "discente")
@Table(name = "discente")
public class Discente extends Usuario{
    public Discente() {
        super();
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
