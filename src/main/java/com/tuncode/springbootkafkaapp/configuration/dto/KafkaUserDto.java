package com.tuncode.springbootkafkaapp.configuration.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author caksuna on 13.05.2024 23:44
 */

@Getter
@Setter
public class KafkaUserDto {

    private String firstName;
    private String lastName;
    private String email;

}
