package com.screamer.resume.service;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.MessageDTO;
import com.screamer.resume.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    final
    MessageRepository messageRepo;

    public MessageServiceImpl(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> getAllSavedMessages() {
        return messageRepo.findAll();
    }

    @Override
    public List<Message> getAllUnreadMessage(){
        return messageRepo.findAllByReadIsFalse();
    }

    @Override
    public Message saveNewMessage(Message m) {
        return messageRepo.save(m);
    }

    @Override
    public Message saveNewMessage(MessageDTO messageDTO) {
        Message messageFromDTO = new Message(messageDTO);
        return saveNewMessage(messageFromDTO);
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
