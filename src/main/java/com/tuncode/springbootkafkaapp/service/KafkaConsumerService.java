package com.tuncode.springbootkafkaapp.service;

import com.tuncode.springbootkafkaapp.configuration.dto.KafkaUserDto;
import com.tuncode.springbootkafkaapp.configuration.exceptions.SourceNotFoundException;
import com.tuncode.springbootkafkaapp.configuration.mapper.IKafkaUserMapper;
import com.tuncode.springbootkafkaapp.entity.DLTErrorMessages;
import com.tuncode.springbootkafkaapp.repository.DLTErrorMessagesRepository;
import com.tuncode.springbootkafkaapp.repository.KafkaUserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


/**
 * @author caksuna on 25.06.2024 16:34
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final DLTErrorMessagesRepository dltErrorMessagesRepository;

    @RetryableTopic(
            attempts = "5",
            include = {SourceNotFoundException.class},
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltTopicSuffix = "-custom-deadletter",
            retryTopicSuffix = "-custom-retrytopic",
            backoff = @Backoff(delay = 5000, multiplier = 1) // Duration 5000 MS 
    )
    @KafkaListener(topics = "user-create", groupId = "tuncode")
    public void listenTopic(KafkaUserDto userDto, Acknowledgment ack) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            log.error("Message not consumed !");
            throw new SourceNotFoundException("Source Not Found !");
        }
        log.info("Consumed: {}", userDto);
        ack.acknowledge();
    }

    @DltHandler
    public void handleDltQueueMessages(KafkaUserDto data, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        DLTErrorMessages dltErrorMessages = IKafkaUserMapper.KAFKA_USER_MAPPER.mapToDLTErrorMessage(data);
        if (dltErrorMessages == null) {
            log.error("Mapping failed for data: {}", data);
            return;
        }

        dltErrorMessages.setMessage(String.format(
                "Message not consumed! Data: '%s %s %s' sent to %s",
                data.getFirstName(), data.getLastName(), data.getEmail(), topic
        ));
        dltErrorMessages.setMessageConsumed(false);
        dltErrorMessages.setCreationTime(LocalDateTime.now());

        try {
            dltErrorMessagesRepository.save(dltErrorMessages);
            log.info("Data saved to database: {}", data);
        } catch (Exception e) {
            log.error("Error saving DLT message to database: {}", e.getMessage());
        }
    }
}
