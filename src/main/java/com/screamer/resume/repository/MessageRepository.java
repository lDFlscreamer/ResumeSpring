package com.screamer.resume.repository;

import com.screamer.resume.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAllByReadIsFalse();

    List<Message> findAllByAuthorId(String authorId);
    List<Message> findAllByAuthorIdAndReadIsFalse(String authorId);
    List<Message> findAllByDirectIsFalse();
    List<Message> findAllByDirectIsTrue();
    List<Message> findAllByDirectIsFalseAndAuthorId(String authorId);

}
