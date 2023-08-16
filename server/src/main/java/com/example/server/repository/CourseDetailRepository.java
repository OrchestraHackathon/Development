package com.example.server.repository;

import com.example.server.domain.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {

    List<CourseDetail> findByUsersIdAndCourseId(Long usersId, Long courseId);
}
