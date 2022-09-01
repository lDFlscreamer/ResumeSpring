package com.screamer.resume;

import com.screamer.resume.config.MongoConfig;
import com.screamer.resume.config.SecurityConfig;
import com.screamer.resume.config.listeners.MessageCascadeMongoEventListener;
import com.screamer.resume.config.listeners.UserCascadeMongoEventListener;
import com.screamer.resume.controller.restController.AnswerRestController;
import com.screamer.resume.controller.restController.MessageRestController;
import com.screamer.resume.controller.restController.ResumeRestController;
import com.screamer.resume.controller.restController.UserResumeRestController;
import com.screamer.resume.repository.AnswerRepository;
import com.screamer.resume.repository.MessageRepository;
import com.screamer.resume.repository.UserRepository;
import com.screamer.resume.service.businessServices.answer.AnswerService;
import com.screamer.resume.service.businessServices.message.MessageService;
import com.screamer.resume.service.businessServices.resume.ResumeService;
import com.screamer.resume.service.businessServices.resume.UserResumeService;
import com.screamer.resume.service.dbServices.message.MessageDbService;
import com.screamer.resume.service.dbServices.resume.ResumeDbService;
import com.screamer.resume.service.dbServices.user.UserDbService;
import com.screamer.resume.utils.ResumeEncoder;
import com.screamer.resume.utils.ResumeFabric;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResumeApplicationTests {
    @Test
    void contextLoads(ApplicationContext context) {
        assertNotNull(context, "ApplicationContext is null");

        assertNotNull(context.getBean(MongoConfig.class), "MongoConfig is null");
        assertNotNull(context.getBean(MongoTransactionManager.class), "MongoTransactionManager is null");
        assertNotNull(context.getBean(MessageCascadeMongoEventListener.class), "MessageCascadeMongoEventListener is null");
        assertNotNull(context.getBean(UserCascadeMongoEventListener.class), "UserCascadeMongoEventListener is null");

        assertNotNull(context.getBean(SecurityConfig.class), "SecurityConfig is null");
        assertNotNull(context.getBean(DefaultSecurityFilterChain.class), "DefaultSecurityFilterChain is null");
        assertNotNull(context.getBean(JwtDecoder.class), "JwtDecoder is null");
        assertNotNull(context.getBean("corsConfigurer"), "corsConfigurer is null");

        assertNotNull(context.getBean(MessageRestController.class), "MessageRestController is null");
        assertNotNull(context.getBean(MessageService.class), "MessageService is null");
        assertNotNull(context.getBean(MessageDbService.class), "MessageDbService is null");
        assertNotNull(context.getBean(MessageRepository.class), "MessageRepository is null");

        assertNotNull(context.getBean(AnswerRestController.class), "AnswerRestController is null");
        assertNotNull(context.getBean(AnswerService.class), "AnswerService is null");
        assertNotNull(context.getBean(AnswerRepository.class), "AnswerRepository is null");

        assertNotNull(context.getBean(ResumeRestController.class), "ResumeRestController is null");
        assertNotNull(context.getBean(ResumeService.class), "ResumeService is null");
        assertNotNull(context.getBean(ResumeDbService.class), "ResumeDbService is null");
        assertNotNull(context.getBean(ResumeFabric.class), "ResumeFabric is null");
        assertNotNull(context.getBean(ResumeEncoder.class), "ResumeEncoder is null");

        assertNotNull(context.getBean(UserResumeRestController.class), "UserResumeRestController is null");
        assertNotNull(context.getBean(UserResumeService.class), "UserResumeService is null");
        assertNotNull(context.getBean(UserDbService.class), "UserDbService is null");
        assertNotNull(context.getBean(UserRepository.class), "UserRepository is null");
    }

}
