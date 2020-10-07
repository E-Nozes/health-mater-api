package br.com.fiap.healthmater.model;

import br.com.fiap.healthmater.enumerator.DonationTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @Column(name = "donation_id")
    private Integer id;

    @Size(max = 200)
    @Column(length = 200)
    private String description;

    @NotNull
    @Enumerated
    @Column(nullable = false)
    private DonationTypeEnum type;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate donationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DonationTypeEnum getType() {
        return type;
    }

    public void setType(DonationTypeEnum type) {
        this.type = type;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", donationDate=" + donationDate +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donation)) return false;
        Donation donation = (Donation) o;
        return getId().equals(donation.getId()) &&
                getType() == donation.getType() &&
                getUser().equals(donation.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getUser());
    }

}
