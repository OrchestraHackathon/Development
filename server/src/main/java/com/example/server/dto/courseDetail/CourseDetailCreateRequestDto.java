package com.example.server.dto.courseDetail;

import com.example.server.domain.*;
import com.example.server.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailCreateRequestDto {

    private int day;
    private String startTime;
    private String endTime;

    public CourseDetail toEntity(Users users, Course course) {
        return CourseDetail.builder()
                .users(users)
                .course(course)
                .day(day)
                .startTime(startTime)
                .endTime(endTime)
                .status(Status.ACTIVE)
                .build();
    }
}
