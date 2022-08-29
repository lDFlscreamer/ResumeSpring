package com.screamer.resume.utils.listeners;

import com.screamer.resume.entity.Resume;
import com.screamer.resume.entity.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;

import java.util.List;

public class UserCascadeMongoEventListener extends AbstractMongoEventListener<User> {
    @Autowired
    private MongoOperations mongoOperations;


    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        User source = event.getSource();
        if (isResumeListExistAndIsNotEmpty(source)) {
            saveResumeListToDb(source);
        }
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<User> event) {
        Document source = event.getSource();
        Object sourceId = source.get("_id");
        User user = mongoOperations.findById(sourceId, User.class);
        if (user != null) {
            if (isResumeListExistAndIsNotEmpty(user)) {
                removeResumeListFromDb(user);
            }
        }
        super.onBeforeDelete(event);
    }

    private boolean isResumeListExistAndIsNotEmpty(User source) {
        List<Resume> resumeList = source.getResumeList();
        boolean isListExist = (resumeList != null);
        if (!isListExist) {
            return false;
        }
        return (!resumeList.isEmpty());
    }

    private void saveResumeListToDb(User user) {
        List<Resume> resumeList = user.getResumeList();
        for (Resume resume :
                resumeList) {
            mongoOperations.save(resume);
        }
    }

    private void removeResumeListFromDb(User user) {
        List<Resume> resumeList = user.getResumeList();
        for (Resume resume :
                resumeList) {
            mongoOperations.remove(resume);
        }
    }
}