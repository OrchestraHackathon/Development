package com.example.server.dto.course;

import com.example.server.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseReadResponseDto {

    private Long courseId;
    private String courseName;
    private String professor;
    private String categoryName;
    private String courseSummary;
    private Long registerPeople;

    public CourseReadResponseDto (Course course, Long registerPeople) {
        this.courseId = course.getId();
        this.courseName = course.getName();
        this.professor = course.getUsers().getName();
        this.categoryName = course.getCourseCategories().get(0).getCategory().getName();
        this.courseSummary = course.getSummary();
        this.registerPeople = registerPeople;
    }
}
