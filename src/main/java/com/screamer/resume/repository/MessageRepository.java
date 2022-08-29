package com.screamer.resume.repository;

import com.screamer.resume.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAllByReadIsFalse();

    List<Message> findAllByAuthor_UserAuthId(String authorId);

    List<Message> findAllByAuthor_UserAuthIdAndReadIsFalse(String authorId);

    List<Message> findAllByDirectIsFalse();
}
