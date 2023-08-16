package com.example.server.domain;

import com.example.server.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Course extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String detail;
    @Column(nullable = false)
    private String summary;

    //  name(과목명) 추가
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // 연관 관계 Mapping
    @OneToMany(mappedBy = "course")
    private List<CourseDetail> courseDetails = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "course")
    private List<CourseCategory> courseCategories = new ArrayList<>();

    @Builder
    public Course(String detail, String summary, String name, Status status) {
        this.detail = detail;
        this.summary = summary;
        this.name = name;
        this.status = status;
    }

    public void addCourseDetail(CourseDetail courseDetail) {
        this.courseDetails.add(courseDetail);
    }
    public void addCourseCategory(CourseCategory courseCategory) {
        this.courseCategories.add(courseCategory);
    }
}
