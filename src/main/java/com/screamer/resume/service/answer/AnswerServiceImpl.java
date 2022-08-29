package com.screamer.resume.service.answer;

import com.screamer.resume.entity.Answer;
import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.exceptions.message.MessageUnansweredException;
import com.screamer.resume.service.dbService.message.MessageDbService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final MessageDbService messageDbService;

    public AnswerServiceImpl(MessageDbService messageDbService) {
        this.messageDbService = messageDbService;
    }

    @Override
    public Message answerToMessage(String messageId, String answerText) throws MessageNotFoundException {
        Message message = messageDbService.getMessageById(messageId);
        Answer answer = new Answer(answerText);

        message.setAnswer(answer);

        return messageDbService.updateMessage(message);
    }

    @Override
    public Message updateAnswer(String messageId, String answerText) throws MessageNotFoundException, MessageUnansweredException {
        Message message = messageDbService.getMessageById(messageId);

        Answer answer = message.getAnswer();
        if (answer == null) {
            throw new MessageUnansweredException(messageId);
        }

        answer.setAnswerText(answerText);
        message.setAnswer(answer);

        return messageDbService.updateMessage(message);
    }

}
