package com.tuncode.springbootkafkaapp.service;

import com.tuncode.springbootkafkaapp.entity.KafkaUser;

/**
 * @author caksuna on 13.05.2024 23:42
 */
public interface KafkaUserService {

    void createKafkaUser(KafkaUser kafkaUser);
}
