package com.example.server.dto.timeTableCourseDetail;

import com.example.server.domain.CourseDetail;
import com.example.server.domain.Status;
import com.example.server.domain.TimeTable;
import com.example.server.domain.TimeTableCourseDetail;
import com.example.server.domain.users.Users;
import com.example.server.dto.courseDetail.CourseDetailCreateRequestDto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeTableCourseDetailCreateRequestDto {

    List<CourseDetailCreateRequestDto> courseDetails;

    public TimeTableCourseDetail toEntity(TimeTable timeTable, CourseDetail courseDetail) {
        return TimeTableCourseDetail.builder()
                .timeTable(timeTable)
                .courseDetail(courseDetail)
                .status(Status.ACTIVE)
                .build();
    }
}
