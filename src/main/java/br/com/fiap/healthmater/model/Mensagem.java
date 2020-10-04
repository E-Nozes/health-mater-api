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
@Table(name = "tb_mensagem")
public class Mensagem {

    @Id
    @Column(name = "id_mensagem")
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "ds_mensagem", length = 100)
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_conversa", nullable = false)
    private ConversaWatson conversa;

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

    public ConversaWatson getConversa() {
        return conversa;
    }

    public void setConversa(ConversaWatson conversa) {
        this.conversa = conversa;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", conversa=" + conversa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensagem)) return false;
        Mensagem mensagem = (Mensagem) o;
        return getId().equals(mensagem.getId()) &&
                getConversa().equals(mensagem.getConversa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConversa());
    }

}
