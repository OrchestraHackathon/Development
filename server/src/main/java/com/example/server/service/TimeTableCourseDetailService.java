package com.example.server.service;

import com.example.server.domain.Course;
import com.example.server.domain.CourseDetail;
import com.example.server.domain.TimeTable;
import com.example.server.domain.TimeTableCourseDetail;
import com.example.server.domain.users.Users;
import com.example.server.dto.courseDetail.CourseDetailCreateRequestDto;
import com.example.server.dto.timeTableCourseDetail.TimeTableCourseDetailCreateRequestDto;
import com.example.server.repository.*;
import com.example.server.util.exception.CourseException;
import com.example.server.util.exception.TimeTableException;
import com.example.server.util.exception.UsersException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.server.util.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TimeTableCourseDetailService {

    private final UsersRepository usersRepository;
    private final CourseRepository courseRepository;
    private final CourseDetailRepository courseDetailRepository;
    private final TimeTableRepository timeTableRepository;
    private final TimeTableCourseDetailRepository timeTableCourseDetailRepository;

    // TimeTableCourseDetail 생성
    public void save(Long usersId, Long courseId, TimeTableCourseDetailCreateRequestDto requestDto) {
        Users users = usersRepository.findById(usersId)
                .orElseThrow(() -> new UsersException(NONE_USER));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(NONE_COURSE));

        TimeTable timeTable = timeTableRepository.findByUsersId(usersId)
                .orElseThrow(() -> new TimeTableException(NONE_TIMETABLE));

        List<CourseDetailCreateRequestDto> courseDetailCreateRequestDtos = requestDto.getCourseDetails();
        
        List<CourseDetail> courseDetails = courseDetailCreateRequestDtos.stream()
                .map(c -> c.toEntity(users, course))
                .collect(Collectors.toList());

        courseDetails.forEach(users::addCourseDetail);
        courseDetails.forEach(course::addCourseDetail);


        courseDetailRepository.saveAll(courseDetails);

        for (CourseDetail courseDetail : courseDetails) {
            log.info("courseDetail.getId() = {}", courseDetail.getId());
        }

        List<TimeTableCourseDetail> timeTableCourseDetails = courseDetails.stream()
                .map(c -> requestDto.toEntity(timeTable, c))
                .collect(Collectors.toList());

        for(TimeTableCourseDetail timeTableCourseDetail : timeTableCourseDetails) {
            for (CourseDetail courseDetail : courseDetails) {
                courseDetail.addTimeTableCourseDetail(timeTableCourseDetail);
            }
        }

        timeTableCourseDetailRepository.saveAll(timeTableCourseDetails);

        for (TimeTableCourseDetail timeTableCourseDetail : timeTableCourseDetails) {
            log.info("timeTableCourseDetailId = {}", timeTableCourseDetail.getId());
        }

        return;
    }
}
