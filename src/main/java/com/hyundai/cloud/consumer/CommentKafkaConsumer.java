package com.hyundai.cloud.consumer;

import com.hyundai.cloud.document.CommentDocument;
import com.hyundai.cloud.entity.CommentEntity;
import com.hyundai.cloud.repository.CommentMongoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CommentKafkaConsumer {
    @Autowired
    private CommentMongoRepository commentMongoRepository;

    @KafkaListener(topics = "comment-topic", groupId = "cloud-group")
    public void consumeComment(CommentEntity entity) {
        CommentDocument doc = new CommentDocument();
        BeanUtils.copyProperties(entity, doc);
        doc.setCommentId(String.valueOf(entity.getCommentId()));
        commentMongoRepository.save(doc);
    }
}
