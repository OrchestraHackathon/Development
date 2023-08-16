package com.example.server.service;

import com.example.server.domain.CourseDetail;
import com.example.server.domain.Status;
import com.example.server.domain.TimeTable;
import com.example.server.domain.TimeTableCourseDetail;
import com.example.server.domain.users.Users;
import com.example.server.dto.timeTableCourseDetail.TimeTableCourseDetailReadResponseDto;
import com.example.server.dto.timetable.TimeTableReadResponseDto;
import com.example.server.dto.timetable.TimeTableResponseDto;
import com.example.server.repository.CourseDetailRepository;
import com.example.server.repository.TimeTableCourseDetailRepository;
import com.example.server.repository.TimeTableRepository;
import com.example.server.repository.UsersRepository;
import com.example.server.util.exception.TimeTableException;
import com.example.server.util.exception.UsersException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.server.util.BaseResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final UsersRepository usersRepository;
    private final TimeTableCourseDetailRepository timeTableCourseDetailRepository;
    private final CourseDetailRepository courseDetailRepository;

    public TimeTableResponseDto save(Long usersId) {
        Users users = usersRepository.findById(usersId)
                .orElseThrow(() -> new UsersException(NONE_USER));

        TimeTable timeTable = TimeTable.builder()
                .name("DefaultTimeTable")
                .users(users)
                .status(Status.ACTIVE)
                .build();

        timeTableRepository.save(timeTable);

        return new TimeTableResponseDto(timeTable);
    }

    public TimeTableReadResponseDto read(Long usersId) {
        Users users = usersRepository.findById(usersId)
                .orElseThrow(() -> new UsersException(NONE_USER));

        TimeTable timeTable = timeTableRepository.findByUsersId(usersId)
                .orElseThrow(() -> new TimeTableException(NONE_TIMETABLE));

        List<TimeTableCourseDetail> timeTableCourseDetails = timeTableCourseDetailRepository.findByTimeTableId(timeTable.getId());

        List<CourseDetail> courseDetails = timeTableCourseDetails.stream()
                .map(timeTableCourseDetail -> timeTableCourseDetail.getCourseDetail())
                .collect(Collectors.toList());

        List<TimeTableCourseDetailReadResponseDto> timeTableCourseDetailReadResponseDtos = courseDetails.stream()
                .map(courseDetail -> TimeTableCourseDetailReadResponseDto.of(courseDetail))
                .collect(Collectors.toList());

        return new TimeTableReadResponseDto(timeTable.getName(), timeTableCourseDetailReadResponseDtos);
    }
}
