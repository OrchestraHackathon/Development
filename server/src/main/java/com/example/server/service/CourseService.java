package com.example.server.service;

import com.example.server.domain.*;
import com.example.server.domain.users.Users;
import com.example.server.dto.SliceResponse;
import com.example.server.dto.course.CourseCreateRequestDto;
import com.example.server.dto.course.CourseReadResponseDto;
import com.example.server.repository.*;
import com.example.server.util.exception.UsersException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.server.util.BaseResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseCategoryRepository courseCategoryRepository;
    private final UsersRepository usersRepository;
    private final CategoryRepository categoryRepository;
    private final CourseDetailRepository courseDetailRepository;

    public void save(Long usersId, CourseCreateRequestDto requestDto) {
        Users users = usersRepository.findById(usersId)
                .orElseThrow(() -> new UsersException(NONE_USER));

        Course course = requestDto.toEntity(users);
        courseRepository.save(course);

        List<Category> categories = requestDto.getCategories().stream()
                .map(id -> categoryRepository.findById(id.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 category 입니다.")))
                .collect(Collectors.toList());

        List<CourseCategory> courseCategories = categories.stream()
                .map(c -> new CourseCategory(course, c))
                .collect(Collectors.toList());

        courseCategoryRepository.saveAll(courseCategories);

        for (CourseCategory courseCategory : courseCategories) {
            course.addCourseCategory(courseCategory);
        }

        return;
    }

    @Transactional(readOnly = true)
    public SliceResponse<CourseReadResponseDto> searchByName(String keyword, Pageable pageable) {

        Slice<Course> allCoursesSliceBy = courseRepository.findByNameContaining(keyword, pageable);
        List<Course> courses = allCoursesSliceBy.getContent();
        List<Long> registerPeopleList = courses.stream()
                .map(c -> courseDetailRepository.countByCourseId(c.getId()))
                .collect(Collectors.toList());
        List<CourseReadResponseDto> responseDtos = new ArrayList<>();
        for (int i=0; i<courses.size(); i++) {
            responseDtos.add(new CourseReadResponseDto(courses.get(i), registerPeopleList.get(i)));
        }

        return new SliceResponse<>(responseDtos, allCoursesSliceBy.getPageable().getPageNumber(), allCoursesSliceBy.isLast());
    }

}
