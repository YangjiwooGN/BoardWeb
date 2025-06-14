package com.hyundai.cloud.repository;

import com.hyundai.cloud.document.BoardDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMongoRepository extends MongoRepository<BoardDocument, String> {
}
