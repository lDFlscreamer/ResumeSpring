package com.screamer.resume.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends MessageDTO {
    @Id
    public String _id;
    public long timestamp;
    public boolean read;

    public Message() {
        super();
        this._id = UUID.randomUUID().toString();
        this.timestamp = Instant.now().getEpochSecond();
        this.read = false;
    }

    public Message(MessageDTO messageDTO) {
        this();
        this.author = messageDTO.author;
        this.title = messageDTO.title;
        this.content = messageDTO.content;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Message{"
                .concat("id='").concat(_id).concat("'")
                .concat(", author='").concat(author).concat("'")
                .concat(", title='").concat(title).concat("'")
                .concat(", content='").concat(content).concat("'")
                .concat("isRead=").concat(read ? "yes" : "No").concat("'")
                .concat("}");
    }
}
