package br.com.fiap.healthmater.entity;

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
@Table
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotEmpty
    @Size(min = 9, max = 9)
    @Column(length = 9, nullable = false)
    private String zipCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    private Address(@NotEmpty @Size(max = 150) String address, @NotEmpty @Size(max = 10) String number, @NotEmpty @Size(min = 9, max = 9) String zipCode, @NotNull City city) {
        this.address = address;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Address() {
    }

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
                ", zipCode='" + zipCode + '\'' +
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

    public static class AddressBuilder {

        private String address;
        private String number;
        private String zipCode;
        private City city;

        public AddressBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AddressBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public AddressBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder withCity(City city) {
            this.city = city;
            return this;
        }

        public Address build() {
            return new Address(address, number, zipCode, city);
        }

    }

}
