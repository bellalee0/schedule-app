package com.example.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private String commentCreator;
    @Column(nullable = false)
    private Long commentPassword;
    @Column(nullable = false)
    private Long scheduleId;

    public Comment(String comment, String commentCreator, Long commentPassword, Long scheduleId) {
        this.comment = comment;
        this.commentCreator = commentCreator;
        this.commentPassword = commentPassword;
        this.scheduleId = scheduleId;
    }
}
