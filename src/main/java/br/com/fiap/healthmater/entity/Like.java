package br.com.fiap.healthmater.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Map the 'like' table in the database.
 *
 * @author Gabriel Oliveira
 */
@Entity
@Table(name = "like")
public class Like {

    @Id
    @Column(name = "like_id")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", post=" + post +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like)) return false;
        Like like = (Like) o;
        return getId().equals(like.getId()) &&
                getPost().equals(like.getPost()) &&
                getUser().equals(like.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPost(), getUser());
    }

}
