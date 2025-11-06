package com.example.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  comments 테이블 매핑용 클래스(생성 시, 댓글, 작성자, 비밀번호, 일정ID 포함)
 */
@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false, length = 100)
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

    /**
     * 댓글 수정 메서드
     *
     * @param comment 원하는 comment
     * @param commentCreator 원하는 commentCreator
     */
    public void update(String comment, String commentCreator) {
        this.comment = comment;
        this.commentCreator = commentCreator;
    }
}
