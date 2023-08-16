package com.example.server.controller;

import com.example.server.domain.users.PrincipalDetails;
import com.example.server.dto.course.CourseCreateRequestDto;
import com.example.server.dto.timeTableCourseDetail.TimeTableCourseDetailCreateRequestDto;
import com.example.server.service.CourseService;
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
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/courses")
    public BaseResponse<BaseResponseStatus> save(@RequestBody CourseCreateRequestDto requestDto,
                                                 @AuthenticationPrincipal PrincipalDetails users) {
        Long usersId = users.getId();
        courseService.save(usersId, requestDto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
