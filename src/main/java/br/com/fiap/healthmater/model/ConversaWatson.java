package br.com.fiap.healthmater.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_conversa_watson")
public class ConversaWatson {

    @Id
    @Column(name = "id_conversa")
    private Integer id;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dt_conversa", nullable = false)
    private LocalDate dataConversa;

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

    public LocalDate getDataConversa() {
        return dataConversa;
    }

    public void setDataConversa(LocalDate dataConversa) {
        this.dataConversa = dataConversa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ConversaWatson{" +
                "id=" + id +
                ", dataConversa=" + dataConversa +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConversaWatson)) return false;
        ConversaWatson that = (ConversaWatson) o;
        return getId().equals(that.getId()) &&
                getUsuario().equals(that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario());
    }

}
