package com.tuncode.springbootkafkaapp.configuration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author caksuna on 15.05.2024 11:16
 */

@Data
public class KafkaUserUpdatedDto {

    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;
    private boolean isUpdated;

}
