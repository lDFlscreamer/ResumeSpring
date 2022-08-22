package com.screamer.resume.service.answer;

import com.screamer.resume.entity.Answer;
import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.MessageNotFoundException;
import com.screamer.resume.exceptions.MessageUnansweredException;
import com.screamer.resume.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerDbServiceImpl implements AnswerDbService {

    final
    private MessageRepository messageRepository;

    public AnswerDbServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message answerToMessage(String messageId, String answerText) throws MessageNotFoundException {
        Message message = getMessageById(messageId);
        Answer answer = new Answer(answerText);

        message.setAnswer(answer);

        return messageRepository.save(message);
    }

    @Override
    public Message updateAnswer(String messageId, String answerText) throws MessageNotFoundException, MessageUnansweredException {
        Message message = getMessageById(messageId);

        Answer answer = message.getAnswer();
        if (answer == null) {
            throw new MessageUnansweredException(messageId);
        }

        answer.setAnswerText(answerText);
        message.setAnswer(answer);

        return messageRepository.save(message);
    }

    private Message getMessageById(String messageId) throws MessageNotFoundException {
        Optional<Message> foundedMessage = messageRepository.findById(messageId);
        if (!foundedMessage.isPresent()) {
            throw new MessageNotFoundException(messageId);
        }

        return foundedMessage.get();
    }

}
