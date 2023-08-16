package com.example.server.service;

import com.example.server.repository.CourseDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseDetailService {

    private final CourseDetailRepository courseDetailRepository;
}
