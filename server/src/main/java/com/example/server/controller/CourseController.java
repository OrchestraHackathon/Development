package com.example.server.controller;

import com.example.server.domain.users.PrincipalDetails;
import com.example.server.dto.SliceResponse;
import com.example.server.dto.course.CourseCreateRequestDto;
import com.example.server.dto.course.CourseReadResponseDto;
import com.example.server.dto.timeTableCourseDetail.TimeTableCourseDetailCreateRequestDto;
import com.example.server.service.CourseService;
import com.example.server.util.BaseResponse;
import com.example.server.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @GetMapping(value = "/courses")
    public BaseResponse<SliceResponse<CourseReadResponseDto>> searchCourse(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                           @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                                           @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                                           @AuthenticationPrincipal PrincipalDetails users) {

        PageRequest pageRequest = PageRequest.of(page, size);
        SliceResponse<CourseReadResponseDto> responseDtos = courseService.searchByName(keyword, pageRequest);
        return new BaseResponse<>(responseDtos);
    }
}
