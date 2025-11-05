package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request) {
        CreateCommentResponse result = commentService.createComment(scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
