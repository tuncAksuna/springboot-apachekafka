package com.tuncode.springbootkafkaapp.configuration.mapper;

import com.tuncode.springbootkafkaapp.configuration.dto.KafkaUserDto;
import com.tuncode.springbootkafkaapp.entity.DLTErrorMessages;
import com.tuncode.springbootkafkaapp.entity.KafkaUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author caksuna on 13.05.2024 23:47
 */

@Mapper(componentModel = "spring")
public interface IKafkaUserMapper {

    IKafkaUserMapper KAFKA_USER_MAPPER = Mappers.getMapper(IKafkaUserMapper.class);

    KafkaUser mapToEntity(KafkaUserDto dto);

    KafkaUserDto mapToDtO(KafkaUser kafkaUser);

    DLTErrorMessages mapToDLTErrorMessage(KafkaUserDto dto);

}
