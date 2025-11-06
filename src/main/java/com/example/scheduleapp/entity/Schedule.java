package com.example.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  schedules 테이블 매핑용 클래스(생성 시, 일정 제목, 일정 내용, 작성자, 비밀번호 포함)
 */
@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 200)
    private String contents;
    @Column(nullable = false)
    private String creator;
    @Column(nullable = false)
    private Long password;

    public Schedule(String title, String contents, String creator, Long password) {
        this.title = title;
        this.contents = contents;
        this.creator = creator;
        this.password = password;
    }

    // PUT(update) 진행 시, title, creator 수정
    public void update(String title, String creator) {
        this.title = title;
        this.creator = creator;
    }
}
