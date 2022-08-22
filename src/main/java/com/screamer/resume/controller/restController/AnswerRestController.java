package com.screamer.resume.controller.restController;

import com.screamer.resume.entity.Message;
import com.screamer.resume.exceptions.MessageNotFoundException;
import com.screamer.resume.exceptions.MessageUnansweredException;
import com.screamer.resume.service.answer.AnswerDbService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class AnswerRestController {

    final
    AnswerDbService answerDbService;

    public AnswerRestController(AnswerDbService answerDbService) {
        this.answerDbService = answerDbService;
    }

    @PostMapping("/{messageId}/Answer")
    @ResponseStatus(value = HttpStatus.OK)
    public Message answerToMessage(@PathVariable String messageId, @RequestBody String answerText) {
        try {
            return answerDbService.answerToMessage(messageId, answerText);
        } catch (MessageNotFoundException e) {
            // TODO: 15.08.2022 Write exception for this business logic
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{messageId}/Answer")
    @ResponseStatus(value = HttpStatus.OK)
    public Message updateAnswer(@PathVariable String messageId, @RequestBody String answerText) {
        try {
            return answerDbService.updateAnswer(messageId, answerText);
        } catch (MessageNotFoundException e) {
            // TODO: 15.08.2022 Write exception for this business logic
            throw new RuntimeException(e);
        } catch (MessageUnansweredException e) {
            // TODO: 15.08.2022 Write exception for this business logic
            throw new RuntimeException(e);
        }
    }

}
