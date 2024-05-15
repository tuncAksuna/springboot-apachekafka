package com.tuncode.springbootkafkaapp.configuration.dto;

import lombok.Data;

/**
 * @author caksuna on 13.05.2024 23:44
 */

@Data
public class KafkaUserDto {

    private String firstName;
    private String lastName;
    private String email;

}
