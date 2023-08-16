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
@Builder
public class TimeTable extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // 연관 관계 Mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "timeTable")
    private List<TimeTableCourseDetail> timeTableCourseDetails = new ArrayList<>();

    public void addTimeTableCourseDetail(TimeTableCourseDetail timeTableCourseDetail) {
        this.timeTableCourseDetails.add(timeTableCourseDetail);
    }
}
