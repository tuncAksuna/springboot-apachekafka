package com.tuncode.springbootkafkaapp.repository;

import com.tuncode.springbootkafkaapp.entity.KafkaUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author caksuna on 14.05.2024 00:01
 */
public interface KafkaUserJpaRepository extends JpaRepository<KafkaUser, Long> {
}
