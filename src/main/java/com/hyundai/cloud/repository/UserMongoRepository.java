package com.hyundai.cloud.repository;

import com.hyundai.cloud.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    boolean existsByUserNickname(String userNickname);
    UserDocument findByUserEmail(String userEmail);
}
