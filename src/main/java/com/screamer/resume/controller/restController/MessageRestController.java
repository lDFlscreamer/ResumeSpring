package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.service.message.MessageDbService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageRestController {

    final
    MessageDbService messageDbService;


    public MessageRestController(MessageDbService messageDbService) {
        this.messageDbService = messageDbService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Message> getAllMessage() {
        return messageDbService.getAllSavedMessages();
    }

    @GetMapping("/public")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Message> getPublicMessage() {
        return messageDbService.getPublicMessage();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Message createNewMessage(Authentication authentication, @RequestBody Message message) {
        if (authentication == null) {
            return messageDbService.saveNewMessage(message);
        }
        return messageDbService.saveNewMessage(authentication.getName(), message);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Message updateMessage(@RequestBody Message message) {
        return messageDbService.updateMessage(message);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMessageById(@PathVariable(name = "id") String id) {
        messageDbService.deleteMessage(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllMessages() {
        messageDbService.deleteAllMessage();
    }

}
