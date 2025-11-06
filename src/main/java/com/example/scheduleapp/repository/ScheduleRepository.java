package com.example.scheduleapp.repository;

import com.example.scheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    /**
     * 해당 creator의 일정 존재 여부 확인
     *
     * @param creator 조회할 작성자명 입력
     * @return 존재 시 true 반환
     */
    boolean existsByCreator(String creator);
}