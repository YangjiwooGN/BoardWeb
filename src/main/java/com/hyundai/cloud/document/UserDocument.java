package com.hyundai.cloud.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "User")
public class UserDocument {
    @Id
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userName;
    private String userPhoneNumber;
    private String userAddress;
}
