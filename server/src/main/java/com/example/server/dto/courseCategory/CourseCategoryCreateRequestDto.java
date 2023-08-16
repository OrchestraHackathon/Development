package com.example.server.dto.courseCategory;

import com.example.server.domain.CourseCategory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseCategoryCreateRequestDto {

    private long categoryId;
}
