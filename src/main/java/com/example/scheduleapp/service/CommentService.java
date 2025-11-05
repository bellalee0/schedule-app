package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.entity.Comment;
import com.example.scheduleapp.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        Comment comment = new Comment(
                request.getComment(), request.getCommentCreator(), request.getCommentPassword(), scheduleId
        );

        // 해당 일정의 댓글 개수 확인
        if (commentRepository.findByScheduleId(scheduleId).size() < 10) {
            Comment savedComment = commentRepository.save(comment);
            return new CreateCommentResponse(
                    savedComment.getCommentId(), savedComment.getScheduleId(), savedComment.getComment(), savedComment.getCommentCreator(), savedComment.getCreatedAt(), savedComment.getModifiedAt()
            );
        } else {
            throw new EntityNotFoundException("한 일정에 댓글은 10개까지만 작성 가능합니다.");
        }
    }
}
