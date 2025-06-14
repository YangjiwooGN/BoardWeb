package com.hyundai.cloud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private int boardNumber;
    private String userEmail;
    private String commentContent;
    private String commentUserNickname;
    private LocalDateTime commentWriteDate;

    @PrePersist
    public void prePersist() {
        this.commentWriteDate = LocalDateTime.now();
    }
}

