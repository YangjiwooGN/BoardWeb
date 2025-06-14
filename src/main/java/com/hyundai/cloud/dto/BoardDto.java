package com.hyundai.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private String boardTitle;
    private String boardContent;
    private String boardWriterEmail;
    private String boardWriterNickname;
    private String boardWriteDate;
    private int boardClickCount;
}
