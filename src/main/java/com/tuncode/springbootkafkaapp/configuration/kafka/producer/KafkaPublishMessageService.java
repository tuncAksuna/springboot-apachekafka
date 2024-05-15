package com.tuncode.springbootkafkaapp.configuration.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


/**
 * @author caksuna on 12.05.2024 22:17
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaPublishMessageService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToPartition(String topic, String message, int partition) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, message, partition);
        kafkaTemplate.send(record);
    }

    public void sendMessage(GenericMessage genericMessage) {
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(genericMessage);

        // 'whenCompleteAsync' was used for tasks that may require intensive performance in the future
        completableFuture.whenCompleteAsync((result, exc) -> {
            RecordMetadata metadata = result.getRecordMetadata();
            if (exc == null) {
                log.info("Message: {} published, topic: {}, partition: {} and offset: {}",
                        genericMessage,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset());
            } else {
                log.error("Unable to sent message: {} to topic --> {} , due to {}",
                        genericMessage,
                        metadata.topic(),
                        exc.getMessage());
            }
        });
    }
}
