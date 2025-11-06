package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.exception.IncorrectPassword;
import com.example.scheduleapp.exception.NotFoundSchedule;
import com.example.scheduleapp.repository.CommentRepository;
import com.example.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    /**
     * 일정 생성하기
     *
     * @param request HTTP의 body로 전달된 내용을 request DTO로 받아오기
     * @return 리포지토리에 저장 후 response DTO에 담아서 반환
     */
    @Transactional
    public CreateScheduleResponse create(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(), request.getContents(), request.getCreator(), request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getScheduleId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getCreator(), savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt()
        );
    }

    /**
     * 전체 일정 조회하기
     *
     * @param creator Request 파라미터로 작성자명 받기(선택)
     * @return 수정일 기준 내림차순 정렬 후 response DTO에 리스트로 담아서 반환
     */
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String creator) {
        List<GetScheduleResponse> result = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAll();

        // TODO: 더 깔끔하게 만들 수 있을 것 같음
        if (creator == null) {
            for (Schedule schedule : schedules) {
                result.add(new GetScheduleResponse(
                        schedule.getScheduleId(), schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt()
                ));
            }
        } else if (scheduleRepository.existsByCreator(creator)) {
            for (Schedule schedule : schedules) {
                if (schedule.getCreator().equals(creator)) {
                    result.add(new GetScheduleResponse(
                            schedule.getScheduleId(), schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt()
                    ));
                }
            }
        } else {
            throw new NotFoundSchedule("존재하지 않는 사용자입니다.");
        }
        return result.stream().sorted(Comparator.comparing(GetScheduleResponse::getModifiedAt).reversed()).collect(Collectors.toList());
    }

    /**
     * 선택 일정 조회하기
     *
     * @param scheduleId API Path로 일정ID 입력받기
     * @return 해당 일정을 response DTO에 담아서 반환(해당 일정의 댓글 포함)
     */
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundSchedule("존재하지 않는 ID입니다."));
        return new GetOneScheduleResponse(
                schedule.getScheduleId(), schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt(), commentRepository.findByScheduleId(scheduleId)
        );
    }

    /**
     * 선택 일정 수정하기
     *
     * @param scheduleId API Path로 일정ID 입력받기
     * @param password Request 파라미터로 비밀번호 받기(필수)
     * @param request HTTP의 body로 전달된 내용을 request DTO로 받아오기
     * @return 수정사항 적용 후 response DTO에 담아서 반환
     * @throws IllegalStateException 비밀번호 불일치 시
     */
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, String password, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundSchedule("존재하지 않는 ID입니다."));

        if (Long.parseLong(password) == schedule.getPassword()) {
            schedule.update(request.getTitle(), request.getCreator());
            schedule.setModiefiedAt(LocalDateTime.now());
        } else {
            throw new IncorrectPassword("비밀번호가 일치하지 않습니다.");
        }

        return new UpdateScheduleResponse(
                schedule.getScheduleId(),schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt()
        );
    }

    /**
     * 선택 일정 삭제하기
     *
     * @param scheduleId API Path로 일정ID 입력받기
     * @param password Request 파라미터로 비밀번호 받기(필수)
     * @return 반환값 없음
     * @throws NotFoundSchedule 존재하지 않는 ID 입력 시
     * @throws IllegalStateException 비밀번호 불일치 시
     */
    @Transactional
    public void deleteSchedule(Long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundSchedule("존재하지 않는 ID입니다."));

        if (Long.parseLong(password) == schedule.getPassword()) {
            scheduleRepository.deleteById(scheduleId);
        } else {
            throw new IncorrectPassword("비밀번호가 일치하지 않습니다.");
        }
    }
}
