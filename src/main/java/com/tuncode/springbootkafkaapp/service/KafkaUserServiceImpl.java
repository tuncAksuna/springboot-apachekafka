package com.tuncode.springbootkafkaapp.service;

import com.tuncode.springbootkafkaapp.configuration.exceptions.SourceNotFoundException;
import com.tuncode.springbootkafkaapp.configuration.kafka.KafkaPublishMessageService;
import com.tuncode.springbootkafkaapp.configuration.kafka.topic.KafkaUserTopic;
import com.tuncode.springbootkafkaapp.configuration.mapper.IKafkaUserMapper;
import com.tuncode.springbootkafkaapp.entity.KafkaUser;
import com.tuncode.springbootkafkaapp.repository.KafkaUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.kafka.support.KafkaHeaders.*;

/**
 * @author caksuna on 13.05.2024 23:43
 */

@Service
@RequiredArgsConstructor
public class KafkaUserServiceImpl implements KafkaUserService {

    private final KafkaPublishMessageService kafkaPublishMessageService;
    private final KafkaUserJpaRepository kafkaUserJpaRepository;

    @Override
    @Transactional
    public void createKafkaUser(KafkaUser kafkaUser) {
        if (kafkaUser != null) {
            KafkaUser savedKafkaUser = kafkaUserJpaRepository.save(kafkaUser);

            Map<String, Object> headers = new HashMap<>();
            headers.put(KEY, "kafka-createuser");
            headers.put(TOPIC, new KafkaUserTopic().kafkaUserCreateTopic().name());
            headers.put(PARTITION, 1);
            kafkaPublishMessageService.sendMessage(new GenericMessage<>(IKafkaUserMapper.KAFKA_USER_MAPPER.mapToDtO(savedKafkaUser), headers));
        }
    }

}
