package com.screamer.resume.entity;

import java.util.Objects;

public class MessageDTO {
    protected String author;
    protected String title;
    protected String content;

    public MessageDTO() {
    }

    public MessageDTO(String author, String title, String content) {
        this();
        this.author = author;
        this.title = title;
        this.content = content;
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

    @Override
    public String toString() {
        return "MessageDTO{"
                .concat("author='").concat(author).concat("'")
                .concat(", title='").concat(title).concat("'")
                .concat(", content='").concat(content).concat("'")
                .concat("}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDTO that = (MessageDTO) o;
        return Objects.equals(author, that.author) && Objects.equals(title, that.title) && Objects.equals(content, that.content);
    }

}
