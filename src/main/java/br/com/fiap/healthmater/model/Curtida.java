package br.com.fiap.healthmater.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_curtida")
public class Curtida {

    @Id
    @Column(name = "id_curtida")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_publicacao", nullable = false)
    private Publicacao publicacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Curtida{" +
                "id=" + id +
                ", publicacao=" + publicacao +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curtida)) return false;
        Curtida curtida = (Curtida) o;
        return getId().equals(curtida.getId()) &&
                getPublicacao().equals(curtida.getPublicacao()) &&
                getUsuario().equals(curtida.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPublicacao(), getUsuario());
    }

}
