package com.screamer.resume.config.listeners;

import com.screamer.resume.entity.Answer;
import com.screamer.resume.entity.Message;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;

public class MessageCascadeMongoEventListener extends AbstractMongoEventListener<Message> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Message> event) {
        Message source = event.getSource();
        if (isAnswerExist(source)) {
            Answer answer = source.getAnswer();
            mongoOperations.save(answer);
        }
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Message> event) {
        Document source = event.getSource();
        Object sourceId = source.get("_id");
        Message message = mongoOperations.findById(sourceId, Message.class);
        if (message != null) {
            Answer answer = message.getAnswer();
            if (answer != null) {
                mongoOperations.remove(answer);
            }
        }
        super.onBeforeDelete(event);
    }

    private boolean isAnswerExist(Message source) {
        return source.getAnswer() != null;
    }
}