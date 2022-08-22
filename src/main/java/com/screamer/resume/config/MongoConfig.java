package com.screamer.resume.config;

import com.screamer.resume.utils.listeners.MessageCascadeMongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class MongoConfig {

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public MessageCascadeMongoEventListener messageCascadeSaveMongoEventListener() {
        return new MessageCascadeMongoEventListener();
    }

}

