package com.screamer.resume.entity;

public class MessageDTO {
    public String author;
    public String title;
    public String content;

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
}
