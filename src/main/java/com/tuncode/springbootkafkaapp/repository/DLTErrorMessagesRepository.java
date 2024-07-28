package com.tuncode.springbootkafkaapp.repository;

import com.tuncode.springbootkafkaapp.entity.DLTErrorMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DLTErrorMessagesRepository extends JpaRepository<DLTErrorMessages, Long> {
}
