package com.example.server.repository;

import com.example.server.domain.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
}
