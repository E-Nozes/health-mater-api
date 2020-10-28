package br.com.fiap.healthmater.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Map the 'faq' table in the database.
 *
 * @author Gabriel Oliveira
 */
@Entity
@Table
public class Faq {

    @Id
    @Column(name = "faq_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String question;

    @NotEmpty
    @Column(nullable = false)
    private String answer;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate faqDate;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getFaqDate() {
        return faqDate;
    }

    public void setFaqDate(LocalDate faqDate) {
        this.faqDate = faqDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Faq{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", faqDate=" + faqDate +
                ", user=" + user.getEmail() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faq)) return false;
        Faq faq = (Faq) o;
        return getId().equals(faq.getId()) &&
                getUser().equals(faq.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser());
    }

}
