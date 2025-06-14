package com.hyundai.cloud.repository;

import com.hyundai.cloud.document.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMongoRepository extends MongoRepository<CommentDocument, String> {
    List<CommentDocument> findByBoardNumber(int boardNumber);
}