package com.example.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
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
    private List<Integer> days;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
