package com.screamer.resume.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer  {
    @Id
    private String _id;
    private String answerText;
    private long timestamp;
    public Answer() {
        this._id = UUID.randomUUID().toString();
        this.timestamp = Instant.now().getEpochSecond();
    }
    public Answer(String answerContent) {
        this();
        this.answerText = answerContent;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
