package com.screamer.resume.service.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.MessageNotFoundException;
import com.screamer.resume.exceptions.MessageUnansweredException;

public interface AnswerDbService {
    Message answerToMessage(String messageId, String answerText) throws MessageNotFoundException;

    Message updateAnswer(String messageId, String answerText) throws MessageNotFoundException, MessageUnansweredException;
}
