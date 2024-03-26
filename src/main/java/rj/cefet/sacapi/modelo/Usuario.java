package rj.cefet.sacapi.modelo;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "usuario")
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    private UUID matricula;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String senha;
    @Column
    private String cep;
    @Column
    private String endereco;
    @Column(name = "usuario_adm")
    private Boolean usuarioAdm;
    @Column
    private String telefone;

    public Usuario() {
        this.matricula = UUID.randomUUID();
    }

    public UUID getMatricula() {
        return matricula;
    }

    public void setMatricula(UUID matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getUsuarioAdm() {
        return usuarioAdm;
    }

    public void setUsuarioAdm(Boolean usuarioAdm) {
        this.usuarioAdm = usuarioAdm;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getMatricula(), usuario.getMatricula()) && Objects.equals(getNome(), usuario.getNome()) && Objects.equals(getEmail(), usuario.getEmail()) && Objects.equals(getSenha(), usuario.getSenha()) && Objects.equals(getCep(), usuario.getCep()) && Objects.equals(getEndereco(), usuario.getEndereco()) && Objects.equals(getUsuarioAdm(), usuario.getUsuarioAdm()) && Objects.equals(getTelefone(), usuario.getTelefone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricula(), getNome(), getEmail(), getSenha(), getCep(), getEndereco(), getUsuarioAdm(), getTelefone());
    }
}
