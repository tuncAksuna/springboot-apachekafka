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

    @Bean
    public NewTopic kafkaUserCreateTopic() {
        return TopicBuilder.name("user-create")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic kafkaUserUpdateTopic() {
        return TopicBuilder.name("user-update")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
