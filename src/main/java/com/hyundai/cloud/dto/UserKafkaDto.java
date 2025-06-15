package com.hyundai.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKafkaDto {
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userName;
    private String userPhoneNumber;
    private String userAddress;
}

