package com.example.server.domain;

import com.example.server.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CourseDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String startTime;
    @Column(nullable = false)
    private String endTime;

    /**
     * 날짜에 대한 리스트 Integer
     * 일요일 ~ 토요일
     * 0 ~ 6 에 대응
      */
    @Column(nullable = false)
    private int day;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // 연관 관계 Mapping
    @OneToMany(mappedBy = "courseDetail")
    @Builder.Default
    private List<TimeTableCourseDetail> timeTableCourseDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    // 연관 관계 편의 메서드
    public void addTimeTableCourseDetail(TimeTableCourseDetail timeTableCourseDetail) {
        this.timeTableCourseDetails.add(timeTableCourseDetail);
    }
}
