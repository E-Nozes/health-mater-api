package br.com.fiap.healthmater.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Map the 'address' table in the database.
 *
 * @author Gabriel Oliveira
 */
@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    private Integer id;

    @NotEmpty
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String address;

    @NotEmpty
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String number;

    @Size(max = 50)
    @Column(length = 50)
    private String complement;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address1 = (Address) o;
        return getId().equals(address1.getId()) &&
                getAddress().equals(address1.getAddress()) &&
                getCity().equals(address1.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getCity());
    }

}
