package com.screamer.resume.service.dbService.message;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.User;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.repository.MessageRepository;
import com.screamer.resume.service.dbService.user.UserDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageDbServiceImpl implements MessageDbService {

    final
    MessageRepository messageRepository;

    final
    UserDbService userDbService;

    public MessageDbServiceImpl(MessageRepository messageRepository, UserDbService userDbService) {
        this.messageRepository = messageRepository;
        this.userDbService = userDbService;
    }

    @Override
    public List<Message> getAllSavedMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getAllSavedMessages(String author) {
        return messageRepository.findAllByAuthor_UserAuthId(author);
    }

    @Override
    public List<Message> getAllUnreadMessage(){
        return messageRepository.findAllByReadIsFalse();
    }

    @Override
    public List<Message> getAllUnreadMessage(String author){
        return messageRepository.findAllByAuthor_UserAuthIdAndReadIsFalse(author);
    }

    @Override
    public List<Message> getPublicMessage() {
        return messageRepository.findAllByDirectIsFalse();
    }

    @Override
    public Message getMessageById(String messageId) throws MessageNotFoundException {
        Optional<Message> foundedMessage = messageRepository.findById(messageId);
        if (!foundedMessage.isPresent()) {
            throw new MessageNotFoundException(messageId);
        }

        return foundedMessage.get();
    }

    @Override
    public Message saveNewMessage(Message m) {
        return messageRepository.save(m);
    }

    @Override
    public Message saveNewMessage(String authorId,Message m) {
        User author = userDbService.getOrCreate(authorId);
        m.setAuthor(author);
        return saveNewMessage(m);
    }

    @Override
    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(String messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public void deleteAllMessage() {
        messageRepository.deleteAll();
    }
}
