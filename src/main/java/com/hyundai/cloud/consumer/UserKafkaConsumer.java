package com.hyundai.cloud.consumer;

import com.hyundai.cloud.document.UserDocument;
import com.hyundai.cloud.dto.UserKafkaDto;
import com.hyundai.cloud.repository.UserMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(UserKafkaConsumer.class);

    @Autowired
    private UserMongoRepository userMongoRepository;

    @KafkaListener(
            topics = "user-topic",
            groupId = "cloud-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeUser(UserKafkaDto dto) {
        log.info("🔥 Kafka 메시지 수신: {}", dto);
        try {
            UserDocument doc = new UserDocument();
            BeanUtils.copyProperties(dto, doc);
            userMongoRepository.save(doc);
            log.info("✅ MongoDB 저장 완료: {}", doc);
        } catch (Exception e) {
            log.error("❌ MongoDB 저장 실패: {}", e.getMessage(), e);
        }

    }
}