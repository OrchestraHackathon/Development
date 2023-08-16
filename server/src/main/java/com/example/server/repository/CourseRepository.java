package com.example.server.repository;

import com.example.server.domain.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Slice<Course> findByNameContaining(String keyword, Pageable pageable);
}
