package com.hyundai.cloud.consumer;

import com.hyundai.cloud.document.BoardDocument;
import com.hyundai.cloud.entity.BoardEntity;
import com.hyundai.cloud.repository.BoardMongoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BoardKafkaConsumer {
    @Autowired
    private BoardMongoRepository boardMongoRepository;

    @KafkaListener(topics = "board-topic", groupId = "cloud-group")
    public void consumeBoard(BoardEntity entity) {
        BoardDocument doc = new BoardDocument();
        BeanUtils.copyProperties(entity, doc);
        doc.setBoardNumber(String.valueOf(entity.getBoardNumber()));
        boardMongoRepository.save(doc);
    }

    @KafkaListener(topics = "board-delete-topic", groupId = "cloud-group")
    public void consumeDeleteBoard(String boardId) {
        boardMongoRepository.deleteById(boardId);
    }
}