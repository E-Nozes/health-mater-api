package br.com.fiap.healthmater.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Map the 'message' table in the database.
 *
 * @author Gabriel Oliveira
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @Column(name = "message_id")
    private Integer id;

    @NotEmpty
    @Size(max = 250)
    @Column(length = 250)
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "chat_watson_id", nullable = false)
    private ChatWatson chatWatson;

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

    public ChatWatson getChatWatson() {
        return chatWatson;
    }

    public void setChatWatson(ChatWatson chatWatson) {
        this.chatWatson = chatWatson;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", chatWatson=" + chatWatson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getId().equals(message.getId()) &&
                getChatWatson().equals(message.getChatWatson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getChatWatson());
    }

}
