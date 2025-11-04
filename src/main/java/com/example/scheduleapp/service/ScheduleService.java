package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

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

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String creator) {
        List<GetScheduleResponse> result = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAll();

        // creator를 받지 않았을 때 : 전체 항목 result에 저장
        if (creator == null) {
            for (Schedule schedule : schedules) {
                result.add(new GetScheduleResponse(
                        schedule.getScheduleId(), schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt()
                ));
            }
        // creator를 받았을 때 : 해당 creator와 일치하는 항목만 result에 저장
        } else {
            for (Schedule schedule : schedules) {
                if (schedule.getCreator().equals(creator)) {
                    result.add(new GetScheduleResponse(
                            schedule.getScheduleId(), schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt()
                    ));
                }
            }
        }
        // 수정일 기준 내림차순 정렬
        return result.stream().sorted(Comparator.comparing(GetScheduleResponse::getModifiedAt).reversed()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 ID입니다."));
        return new GetScheduleResponse(
                schedule.getScheduleId(), schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt()
        );
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, String password, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 ID입니다."));

        // 비밀번호 일치
        if (Long.parseLong(password) == schedule.getPassword()) {
            schedule.update(request.getTitle(), request.getCreator());
        } else {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 업데이트된 Schedule 반환
        return new UpdateScheduleResponse(
                schedule.getScheduleId(),schedule.getTitle(), schedule.getContents(), schedule.getCreator(), schedule.getCreatedAt(), schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 ID입니다."));

        // 비밀번호 일치 확인
        if (Long.parseLong(password) == schedule.getPassword()) {
            scheduleRepository.deleteById(scheduleId);
        } else {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
}
