package com.screamer.resume.repository;

import com.screamer.resume.entity.ResumeUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<ResumeUser,String> {
}
