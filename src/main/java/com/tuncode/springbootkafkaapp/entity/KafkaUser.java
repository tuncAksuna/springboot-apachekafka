package com.tuncode.springbootkafkaapp.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author caksuna on 13.05.2024 23:55
 */

@Entity
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "USERNAME")
    private String userName;
}
