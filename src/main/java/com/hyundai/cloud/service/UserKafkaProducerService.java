package com.hyundai.cloud.service;

import com.hyundai.cloud.dto.UserKafkaDto;
import com.hyundai.cloud.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaProducerService {

    private static final String TOPIC = "user-topic";

    @Autowired
    private KafkaTemplate<String, UserKafkaDto> kafkaTemplate;

    public void sendUser(UserEntity userEntity) {
        UserKafkaDto dto = new UserKafkaDto();
        BeanUtils.copyProperties(userEntity, dto);
        kafkaTemplate.send(TOPIC, dto);
    }
}

