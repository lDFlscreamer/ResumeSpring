package com.screamer.resume.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String _id;
    private String userAuthId;
    @DBRef
    private List<Resume> resumeList;

    public User() {
        this._id = UUID.randomUUID().toString();
    }

    public User(String userAuthId) {
        this();
        this.userAuthId = userAuthId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserAuthId() {
        return userAuthId;
    }

    public void setUserAuthId(String userAuthId) {
        this.userAuthId = userAuthId;
    }

    public List<Resume> getResumeList() {
        return resumeList;
    }

    public void setResumeList(List<Resume> resumeList) {
        this.resumeList = resumeList;
    }

    public void addResume(Resume resume) {
        if (this.resumeList == null) {
            this.resumeList = new ArrayList<>();
        }
        this.resumeList.add(resume);
    }

    public void removeResume(Resume resume) {
        if (this.resumeList == null) {
            return;
        }
        if (!this.resumeList.contains(resume)) {
            return;
        }
        this.resumeList.remove(resume);
    }


}
