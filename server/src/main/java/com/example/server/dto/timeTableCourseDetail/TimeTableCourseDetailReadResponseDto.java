package com.example.server.dto.timeTableCourseDetail;

import com.example.server.domain.CourseDetail;
import com.example.server.domain.TimeTableCourseDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeTableCourseDetailReadResponseDto {

    private int day;
    private String startTime;
    private String endTime;
    private String courseName;
    private String courseProfessor;
    private long courseId;

    public static TimeTableCourseDetailReadResponseDto of(CourseDetail courseDetail) {
        return TimeTableCourseDetailReadResponseDto.builder()
                .day(courseDetail.getDay())
                .startTime(courseDetail.getStartTime())
                .endTime(courseDetail.getEndTime())
                .courseName(courseDetail.getCourse().getName())
                .courseProfessor(courseDetail.getCourse().getUsers().getName())
                .courseId(courseDetail.getCourse().getId())
                .build();
    }
}
