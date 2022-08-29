package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.message.MessageNotFoundException;
import com.screamer.resume.exceptions.message.MessageUnansweredException;
import com.screamer.resume.service.answer.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("message")
public class AnswerRestController {

    final
    AnswerService answerService;

    public AnswerRestController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{messageId}/answer")
    @ResponseStatus(value = HttpStatus.OK)
    public Message answerToMessage(@PathVariable String messageId, @RequestBody String answerText) {
        try {
            return answerService.answerToMessage(messageId, answerText);
        } catch (MessageNotFoundException e) {
            throw messageNotFoundResponse(e);
        }
    }

    @PutMapping("/{messageId}/answer")
    @ResponseStatus(value = HttpStatus.OK)
    public Message updateAnswer(@PathVariable String messageId, @RequestBody String answerText) {
        try {
            return answerService.updateAnswer(messageId, answerText);
        } catch (MessageNotFoundException e) {
            throw messageNotFoundResponse(e);
        } catch (MessageUnansweredException e) {
            throw messageUnansweredResponse(e);
        }
    }

    private ResponseStatusException messageNotFoundResponse(MessageNotFoundException e) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Message Not Found", e);
    }

    private ResponseStatusException messageUnansweredResponse(MessageUnansweredException e) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Message Answer Not Found", e);
    }

}
