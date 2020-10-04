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
@Table(name = "tb_publicacao")
public class Publicacao {

    @Id
    @Column(name = "id_publicacao")
    private Integer id;

    @NotEmpty
    @Size(max = 300)
    @Column(name = "ds_publicacao", nullable = false, length = 300)
    private String descricao;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Publicacao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publicacao)) return false;
        Publicacao that = (Publicacao) o;
        return getId().equals(that.getId()) &&
                getUsuario().equals(that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario());
    }

}
