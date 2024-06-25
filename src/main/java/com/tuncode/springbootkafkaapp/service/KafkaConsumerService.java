package com.tuncode.springbootkafkaapp.service;

import com.tuncode.springbootkafkaapp.configuration.dto.KafkaUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author caksuna on 25.06.2024 16:34
 */
@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "user-create", groupId = "tuncode")
    public void listenTopic(KafkaUserDto userDto) {
        log.info("Consumed: " + userDto);
    }
}
