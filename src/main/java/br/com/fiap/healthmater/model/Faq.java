package br.com.fiap.healthmater.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_faq")
public class Faq {

    @Id
    @Column(name = "id_faq")
    private Integer id;

    @NotEmpty
    @Size(max = 200)
    @Column(name = "ds_faq", length = 200)
    private String descricao;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dt_faq", nullable = false)
    private LocalDate dataFaq;

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

    public LocalDate getDataFaq() {
        return dataFaq;
    }

    public void setDataFaq(LocalDate dataFaq) {
        this.dataFaq = dataFaq;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Faq{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataFaq=" + dataFaq +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faq)) return false;
        Faq faq = (Faq) o;
        return getId().equals(faq.getId()) &&
                getUsuario().equals(faq.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario());
    }

}
