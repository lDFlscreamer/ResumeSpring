package com.screamer.resume.repository;

import com.screamer.resume.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAllByReadIsFalse();

    List<Message> findAllByAuthor(String author);
    List<Message> findAllByAuthorAndReadIsFalse(String author);

}
