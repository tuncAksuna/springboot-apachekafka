package com.tuncode.springbootkafkaapp.controller;

import com.tuncode.springbootkafkaapp.configuration.dto.KafkaUserDto;
import com.tuncode.springbootkafkaapp.configuration.mapper.IKafkaUserMapper;
import com.tuncode.springbootkafkaapp.configuration.response.AppResponse;
import com.tuncode.springbootkafkaapp.entity.KafkaUser;
import com.tuncode.springbootkafkaapp.service.KafkaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author caksuna on 13.05.2024 16:28
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class KafkaUserController {

    private final KafkaUserService kafkaUserService;

    @PostMapping("/create")
    public AppResponse<KafkaUserDto> createKafkaUser(@RequestBody KafkaUserDto createDto) {
        KafkaUser kafkaUser = IKafkaUserMapper.KAFKA_USER_MAPPER.mapToEntity(createDto);
        kafkaUserService.createKafkaUser(kafkaUser);
        return new AppResponse<>(IKafkaUserMapper.KAFKA_USER_MAPPER.mapToDtO(kafkaUser));
    }

    @PutMapping("/update/{id}")
    public AppResponse<KafkaUserDto> updateKafkaUser(@PathVariable("id") Long id,
                                                     @RequestBody KafkaUserDto kafkaUserDto) {
        KafkaUser kafkaUser = IKafkaUserMapper.KAFKA_USER_MAPPER.mapToEntity(kafkaUserDto);
        kafkaUser.setId(id);
        kafkaUserService.updateKafkaUser(id, kafkaUser);
        return new AppResponse<>(IKafkaUserMapper.KAFKA_USER_MAPPER.mapToDtO(kafkaUser));
    }
}
