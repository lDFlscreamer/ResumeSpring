package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.entity.MessageDTO;
import com.screamer.resume.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageRestController {

    final
    MessageService messageService;


    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Message> getMessage() {
        return messageService.getAllSavedMessages();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Message creteNewMessage(@RequestBody MessageDTO message) {
        return messageService.saveNewMessage(message);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void creteNewMessage(@PathVariable(name = "id") String id) {
        messageService.deleteMessage(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void creteNewMessage() {
        messageService.deleteAllMessage();
    }
}
