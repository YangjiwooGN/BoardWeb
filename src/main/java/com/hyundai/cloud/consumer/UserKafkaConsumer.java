package com.hyundai.cloud.consumer;

import com.hyundai.cloud.document.UserDocument;
import com.hyundai.cloud.repository.UserMongoRepository;
import com.hyundai.cloud.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaConsumer {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @KafkaListener(topics = "user-topic", groupId = "cloud-group")
    public void consumeUser(UserEntity userEntity) {
        UserDocument doc = new UserDocument();
        BeanUtils.copyProperties(userEntity, doc);
        userMongoRepository.save(doc);
    }
}