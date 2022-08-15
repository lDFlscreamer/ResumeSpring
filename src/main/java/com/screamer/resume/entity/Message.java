package com.screamer.resume.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    @Id
    private String _id;
    private String author;
    private String title;
    private String content;
    @DBRef
    private Answer answer;
    private boolean read;

    private long timestamp;

    public Message() {
        super();
        this._id = UUID.randomUUID().toString();
        this.timestamp = Instant.now().getEpochSecond();
        this.read = false;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(_id, message._id);
    }


    @Override
    public String toString() {
        return "Message{".concat("id='").concat(_id).concat("'").concat(", author='").concat(author).concat("'").concat(", title='").concat(title).concat("'").concat(", content='").concat(content).concat("'").concat("isRead=").concat(read ? "yes" : "No").concat("'").concat("}");
    }
}
