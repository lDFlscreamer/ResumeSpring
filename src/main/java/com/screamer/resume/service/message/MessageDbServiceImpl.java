package com.screamer.resume.service.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageDbServiceImpl implements MessageDbService {

    final
    MessageRepository messageRepo;

    public MessageDbServiceImpl(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> getAllSavedMessages() {
        return messageRepo.findAll();
    }

    @Override
    public List<Message> getAllSavedMessages(String author) {
        return messageRepo.findAllByAuthorId(author);
    }

    @Override
    public List<Message> getAllUnreadMessage(){
        return messageRepo.findAllByReadIsFalse();
    }

    @Override
    public List<Message> getAllUnreadMessage(String author){
        return messageRepo.findAllByAuthorIdAndReadIsFalse(author);
    }

    @Override
    public List<Message> getPublicMessage() {
        return messageRepo.findAllByDirectIsFalse();
    }

    @Override
    public Message saveNewMessage(Message m) {
        return messageRepo.save(m);
    }

    @Override
    public Message saveNewMessage(String authorId,Message m) {
        m.setAuthorId(authorId);
        return saveNewMessage(m);
    }

    @Override
    public Message updateMessage(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public void deleteMessage(String messageId) {
        messageRepo.deleteById(messageId);
    }

    @Override
    public void deleteAllMessage() {
        messageRepo.deleteAll();
    }
}
