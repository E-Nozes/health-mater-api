package br.com.fiap.healthmater.entity;

import br.com.fiap.healthmater.enumerator.CurrencyEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Map the 'donation' table in the database.
 *
 * @author Gabriel Oliveira
 */
@Entity
@Table
public class Donation {

    @Id
    @Column(name = "donation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 200)
    @Column(length = 200)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Double amount;

    @NotNull
    @Enumerated
    @Column(nullable = false)
    private CurrencyEnum currency;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
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
                ", amount=" + amount +
                ", currency=" + currency +
                ", donationDate=" + donationDate +
                ", user=" + user.getEmail() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donation)) return false;
        Donation donation = (Donation) o;
        return getId().equals(donation.getId()) &&
                getDonationDate().equals(donation.getDonationDate()) &&
                getUser().equals(donation.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDonationDate(), getUser());
    }

}
