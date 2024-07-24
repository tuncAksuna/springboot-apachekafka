package com.tuncode.springbootkafkaapp.service;

import com.tuncode.springbootkafkaapp.configuration.dto.KafkaUserDto;
import com.tuncode.springbootkafkaapp.configuration.exceptions.SourceNotFoundException;
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


/**
 * @author caksuna on 25.06.2024 16:34
 */
@Service
@Slf4j
public class KafkaConsumerService {

    /**
     * It tries to consume the message 5 times at intervals of 2 times 3 seconds.
     * It only does this when it receives a SourceNotFoundException. If it cannot consume the message, the message is sent to the DeadLetterQueue topic.
     */
    @RetryableTopic(
            attempts = "5",
            include = {SourceNotFoundException.class}, // exception will be thrown only for this exception
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltTopicSuffix = "-custom-deadletter",
            retryTopicSuffix = "-custom-retrytopic",
            backoff = @Backoff(delay = 3000, multiplier = 2)
    )
    @KafkaListener(topics = "user-create", groupId = "tuncode")
    public void listenTopic(KafkaUserDto userDto, Acknowledgment ack) {
        if ("".equals(userDto.getEmail())) {
            log.error("Message not consumed !");
            throw new SourceNotFoundException("Source Not Found !");
        }
        log.info("Consumed: " + userDto);
        ack.acknowledge();
    }

    @DltHandler
    public void handleDltQueueMessages(KafkaUserDto data, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.error("Event from topic: " + topic + " : " + data);
    }

}
