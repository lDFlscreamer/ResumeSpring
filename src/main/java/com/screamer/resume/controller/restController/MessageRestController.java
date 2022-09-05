package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.service.businessServices.message.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageRestController {

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Message> getAllMessage() {
        return messageService.getAllMessage();
    }

    @GetMapping("/public")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Message> getPublicMessage() {
        return messageService.getPublicMessage();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Message createNewMessage(Authentication authentication, @RequestBody Message message) {
        return messageService.createNewMessage(authentication, message);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Message updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMessageById(@PathVariable(name = "id") String id) {
        messageService.deleteMessageById(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllMessages() {
        messageService.deleteAllMessages();
    }

}
