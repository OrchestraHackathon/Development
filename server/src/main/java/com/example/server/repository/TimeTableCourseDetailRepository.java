package com.example.server.repository;

import com.example.server.domain.TimeTableCourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableCourseDetailRepository extends JpaRepository<TimeTableCourseDetail, Long> {

    List<TimeTableCourseDetail> findByTimeTableId(Long timeTableId);
}
