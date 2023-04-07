package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.MessageType;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date_time")
    private Instant createdDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    private MessageType messageType;

    @Column(name = "content")
    private String content;

    @Column(name = "room")
    private String room;

    @Column(name = "username")
    private String username;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Message id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedDateTime() {
        return this.createdDateTime;
    }

    public Message createdDateTime(Instant createdDateTime) {
        this.setCreatedDateTime(createdDateTime);
        return this;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public Message messageType(MessageType messageType) {
        this.setMessageType(messageType);
        return this;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return this.content;
    }

    public Message content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoom() {
        return this.room;
    }

    public Message room(String room) {
        this.setRoom(room);
        return this;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUsername() {
        return this.username;
    }

    public Message username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        return id != null && id.equals(((Message) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", createdDateTime='" + getCreatedDateTime() + "'" +
            ", messageType='" + getMessageType() + "'" +
            ", content='" + getContent() + "'" +
            ", room='" + getRoom() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }
}
