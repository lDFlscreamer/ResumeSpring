package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.service.message.MessageDbService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Message createNewMessage(@RequestBody Message message) {
        return messageDbService.saveNewMessage(message);
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
