package com.screamer.resume.service.answer;

import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.exceptions.message.MessageUnansweredException;

public interface AnswerService {
    Message answerToMessage(String messageId, String answerText) throws MessageNotFoundException;

    Message updateAnswer(String messageId, String answerText) throws MessageNotFoundException, MessageUnansweredException;
}
