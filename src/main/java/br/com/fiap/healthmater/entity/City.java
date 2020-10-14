package br.com.fiap.healthmater.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Map the 'city' table in the database.
 *
 * @author Gabriel Oliveira
 */
@Entity
@Table(name = "city", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class City {

    @Id
    @Column(name = "city_id")
    private Integer id;

    @NotEmpty
    @Size(max = 80)
    @Column(nullable = false, length = 80, unique = true)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId().equals(city.getId()) &&
                getState().equals(city.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getState());
    }

}
