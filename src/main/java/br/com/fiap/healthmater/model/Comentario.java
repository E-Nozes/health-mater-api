package br.com.fiap.healthmater.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tb_comentario")
public class Comentario {

    @Id
    @Column(name = "id_comentario")
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "ds_comentario", length = 100)
    private String descricao;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        return "Comentario{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", publicacao=" + publicacao +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comentario)) return false;
        Comentario that = (Comentario) o;
        return getId().equals(that.getId()) &&
                getPublicacao().equals(that.getPublicacao()) &&
                getUsuario().equals(that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPublicacao(), getUsuario());
    }

}
