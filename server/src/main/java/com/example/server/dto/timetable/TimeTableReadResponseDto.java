package com.example.server.dto.timetable;

import com.example.server.dto.timeTableCourseDetail.TimeTableCourseDetailReadResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableReadResponseDto {

    private String timeTableName;
    private List<TimeTableCourseDetailReadResponseDto> courseInfos;
}
