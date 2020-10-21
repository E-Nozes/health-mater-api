package br.com.fiap.healthmater.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class PostDTO {

    private Integer id;
    private String content;
    private UserDTO author;
    private LocalDateTime dateTime;
    private Set<LikeDTO> likes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Set<LikeDTO> getLikes() {
        return likes;
    }

    public void setLikes(Set<LikeDTO> likes) {
        this.likes = likes;
    }

    public Integer getTotalLikes() {
        return likes.size();
    }

}
