package com.tuncode.springbootkafkaapp.dto;

import lombok.Data;

/**
 * @author caksuna on 13.05.2024 16:30
 */

@Data
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String addressText;
}
