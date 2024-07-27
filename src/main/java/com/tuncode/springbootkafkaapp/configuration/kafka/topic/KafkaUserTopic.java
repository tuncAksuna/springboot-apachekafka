package com.tuncode.springbootkafkaapp.configuration.kafka.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author caksuna on 14.05.2024 13:12
 */

@Configuration
public class KafkaUserTopic {

    /**
     * Since this topic has 3 fields, 3 separate consumer-groups should be created and read. Otherwise lag error may occur
     * "Consumer Group Pub/Sub Model"
     */
    @Bean
    public NewTopic kafkaUserCreateTopic() {
        return TopicBuilder.name("user-create")
                .partitions(3)
                .replicas(3)
                .build();
    }


}
