package com.example.server.dto.course;

import com.example.server.domain.Course;
import com.example.server.domain.Status;
import com.example.server.domain.users.Users;
import com.example.server.dto.courseCategory.CourseCategoryCreateRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseCreateRequestDto {

    private String courseName;
    private String courseSummary;
    private String courseDetail;
    private List<CourseCategoryCreateRequestDto> categories;

    public Course toEntity(Users users) {
        return Course.builder()
                .users(users)
                .name(this.courseName)
                .summary(this.courseSummary)
                .detail(this.courseDetail)
                .status(Status.ACTIVE)
                .build();
    }
}
