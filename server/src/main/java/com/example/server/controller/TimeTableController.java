package com.example.server.controller;

import com.example.server.domain.users.PrincipalDetails;
import com.example.server.dto.timetable.TimeTableResponseDto;
import com.example.server.service.TimeTableService;
import com.example.server.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TimeTableController {

    private final TimeTableService timeTableService;

    @PostMapping(value = "/time-table")
    public BaseResponse<TimeTableResponseDto> createDefaultTimeTable(@AuthenticationPrincipal PrincipalDetails users) {

        Long usersId = users.getId();
        log.info("userId = {}", usersId);
        TimeTableResponseDto responseDto = timeTableService.save(usersId);
        return new BaseResponse<>(responseDto);
    }
}
