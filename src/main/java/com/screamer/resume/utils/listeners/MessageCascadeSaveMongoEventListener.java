package com.screamer.resume.utils.listeners;

import com.screamer.resume.entity.Answer;
import com.screamer.resume.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class MessageCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        boolean isMessage = source instanceof Message;
        if (isMessage && isAnswerExist((Message) source)) {
            Answer answer = ((Message) source).getAnswer();
            mongoOperations.save(answer);
        }
    }

    private boolean isAnswerExist(Message source) {
        return source.getAnswer() != null;
    }
}