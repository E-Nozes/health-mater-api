package br.com.fiap.healthmater.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Integer id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "nome_usuario", nullable = false, length = 50)
    private String nome;

    @Email
    @NotEmpty
    @Size(max = 100)
    @Column(name = "email_usuario", nullable = false, length = 100, unique = true)
    private String email;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "login_usuario", nullable = false, length = 20, unique = true)
    private String login;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "senha_usuario", nullable = false, length = 50)
    private String senha;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "endereco_usuario", nullable = false, length = 100)
    private String endereco;

    @Size(max = 400)
    @Column(name = "descricao_usuario", length = 400)
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getId().equals(usuario.getId()) &&
                getEmail().equals(usuario.getEmail()) &&
                getLogin().equals(usuario.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getLogin());
    }

}
