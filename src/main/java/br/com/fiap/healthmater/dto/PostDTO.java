package br.com.fiap.healthmater.dto;

import br.com.fiap.healthmater.entity.Post;

import java.time.LocalDateTime;

/**
 * Mapper class for {@link Post}.
 *
 * @author Gabriel Oliveira
 */
public class PostDTO {

    private Integer id;
    private String content;
    private UserDTO author;
    private LocalDateTime dateTime;

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

}
