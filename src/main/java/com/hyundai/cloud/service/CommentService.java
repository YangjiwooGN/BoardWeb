package com.hyundai.cloud.service;

import com.hyundai.cloud.entity.CommentEntity;
import com.hyundai.cloud.repository.CommentRepository;
import com.hyundai.cloud.repository.CommentMongoRepository;
import com.hyundai.cloud.document.CommentDocument;
import com.hyundai.cloud.service.KafkaProducerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMongoRepository commentMongoRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public List<CommentDocument> findCommentsByBoardId(int boardId) {
        return commentMongoRepository.findByBoardNumber(boardId);
    }

    @Transactional
    public CommentEntity save(CommentEntity comment) {
        CommentEntity saved = commentRepository.save(comment);
        kafkaProducerService.sendComment(saved);
        return saved;
    }
}