package com.hyundai.cloud.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Board")
public class BoardDocument {
    @Id
    private String boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardWriterEmail;
    private String boardWriterNickname;
    private String boardWriteDate;
    private int boardClickCount;
    private int boardLikeCount;
    private int boardCommentCount;
}