package com.example.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
