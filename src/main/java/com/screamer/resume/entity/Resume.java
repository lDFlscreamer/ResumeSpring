package com.screamer.resume.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resume {
    @Id
    private String _id;
    private ResumeFileType resumeFileType;
    private String position;
    private String resumeFileName;
    private Object resumeFile;

    public Resume() {
        this._id = UUID.randomUUID().toString();
        this.resumeFileType = ResumeFileType.PDF;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ResumeFileType getResumeFileType() {
        return resumeFileType;
    }

    public void setResumeFileType(ResumeFileType resumeFileType) {
        this.resumeFileType = resumeFileType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public Object getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(Object resumeFile) {
        this.resumeFile = resumeFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return _id != null ? _id.equals(resume._id) : resume._id == null;
    }

}
