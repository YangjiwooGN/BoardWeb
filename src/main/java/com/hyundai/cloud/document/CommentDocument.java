package com.hyundai.cloud.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "Comment")
public class CommentDocument {
    @Id
    private String commentId;
    private int boardNumber;
    private String userEmail;
    private String commentContent;
    private String commentUserNickname;
    private LocalDateTime commentWriteDate;
}