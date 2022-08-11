package com.screamer.resume.service;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.MessageDTO;
import com.screamer.resume.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    final
    MessageRepository messageRepo;

    public MessageService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> getAllSavedMessages() {
        return messageRepo.findAll();
    }

    public Message saveNewMessage(Message m) {
        return messageRepo.save(m);
    }

    public Message saveNewMessage(MessageDTO messageDTO) {
        Message messageFromDTO = new Message(messageDTO);
        return saveNewMessage(messageFromDTO);
    }

    public void deleteMessage(String messageId) {
        messageRepo.deleteById(messageId);
    }

    public void deleteAllMessage() {
        messageRepo.deleteAll();
    }
}
