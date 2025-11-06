package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글 생성하기
     *
     * @param scheduleId API Path로 일정ID 입력받기
     * @param request HTTP의 body로 전달된 내용을 request DTO로 받아오기
     * @return Service의 createComment 실행 후 response DTO에 담아서 반환
     */
    @PostMapping("/schedules/{scheduleId}")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request) {
        CreateCommentResponse result = commentService.createComment(scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * 전체 댓글 조회하기
     * @param scheduleId Request 파라미터로 일정ID 받기(선택)
     * @return reponse DTO에 댓글 담아 리스트로 반환
     */
    @GetMapping("/schedules/comments")
    public ResponseEntity<List<GetCommentResponse>> getAllComments(
            @RequestParam(name = "scheduleId", required = false) Long scheduleId
    ) {
        List<GetCommentResponse> result = commentService.getAllComments(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     *  선택 댓글 수정하기
     *
     * @param commentId API Path로 댓글ID 입력받기
     * @param request HTTP의 body로 전달된 내용을 request DTO로 받아오기
     * @return Service의 updateComment 실행 후 response DTO에 담아서 반환
     */
    @PutMapping("/schedules/comments/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequest request) {
        UpdateCommentResponse result = commentService.updateComment(commentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * 선택 댓글 삭제하기
     *
     * @param commentId API Path로 댓글ID 입력받기
     * @param password Request 파라미터로 비밀번호 받기(필수)
     * @return Void 클래스로 빈값 반환
     */
    @DeleteMapping("/schedules/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestParam(name = "password") Long password) {
        commentService.deleteComment(commentId, password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
