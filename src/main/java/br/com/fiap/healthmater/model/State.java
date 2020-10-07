package br.com.fiap.healthmater.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "state", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "federalUnity"})})
public class State {

    @Id
    @Column(name = "state_id")
    private Integer id;

    @NotEmpty
    @Size(max = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @NotEmpty
    @Size(max = 2)
    @Column(nullable = false, length = 2, unique = true)
    private String federalUnity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFederalUnity() {
        return federalUnity;
    }

    public void setFederalUnity(String federalUnity) {
        this.federalUnity = federalUnity;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", federalUnity='" + federalUnity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return getId().equals(state.getId()) &&
                getFederalUnity().equals(state.getFederalUnity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFederalUnity());
    }

}
