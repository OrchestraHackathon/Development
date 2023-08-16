package com.example.server.dto.timetable;

import com.example.server.domain.TimeTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableResponseDto {

    private String name;
    private Long id;

    public TimeTableResponseDto(TimeTable timeTable) {
        this.name = timeTable.getName();
        this.id = timeTable.getId();
    }
}
