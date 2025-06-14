package com.hyundai.cloud.service;

import com.hyundai.cloud.entity.BoardEntity;
import com.hyundai.cloud.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendBoard(BoardEntity board) {
        kafkaTemplate.send("board-topic", board);
    }

    public void sendComment(CommentEntity comment) {
        kafkaTemplate.send("comment-topic", comment);
    }
}