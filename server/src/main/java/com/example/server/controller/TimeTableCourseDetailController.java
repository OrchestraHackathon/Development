package com.example.server.controller;

import com.example.server.domain.TimeTableCourseDetail;
import com.example.server.domain.users.PrincipalDetails;
import com.example.server.dto.timeTableCourseDetail.TimeTableCourseDetailCreateRequestDto;
import com.example.server.dto.users.UsersResponseDto;
import com.example.server.dto.users.UsersSignUpRequestDto;
import com.example.server.service.TimeTableCourseDetailService;
import com.example.server.service.UsersService;
import com.example.server.util.BaseResponse;
import com.example.server.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TimeTableCourseDetailController {

    private final TimeTableCourseDetailService timeTableCourseDetailService;

    @PostMapping("/time-table-course-detail/{courseId}")
    public BaseResponse<BaseResponseStatus> join(@RequestBody TimeTableCourseDetailCreateRequestDto requestDto,
                               @PathVariable Long courseId,
                               @AuthenticationPrincipal PrincipalDetails users) {
        Long usersId = users.getId();
        timeTableCourseDetailService.save(usersId, courseId, requestDto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
