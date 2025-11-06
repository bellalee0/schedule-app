package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.GetCommentResponse;
import com.example.scheduleapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 특정 ScheduleId의 댓글 찾기
     *
     * @param scheduleId 기준 scheduleId 입력
     * @return 해당 댓글들을 각각 response DTO에 담아 리스트로 반환
     */
    List<GetCommentResponse> findByScheduleId(Long scheduleId);

    /**
     * 특정 ScheduleId의 댓글 삭제하기
     *
     * @param scheduleId 기준 scheduleId 입력
     */
    void deleteByScheduleId(Long scheduleId);
}