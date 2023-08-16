package com.example.server.service;

import com.example.server.domain.Status;
import com.example.server.domain.TimeTable;
import com.example.server.domain.users.Users;
import com.example.server.dto.timetable.TimeTableResponseDto;
import com.example.server.repository.TimeTableRepository;
import com.example.server.repository.UsersRepository;
import com.example.server.util.exception.UsersException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.server.util.BaseResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final UsersRepository usersRepository;

    public TimeTableResponseDto save(Long usersId) {
        Users users = usersRepository.findById(usersId)
                .orElseThrow(() -> new UsersException(NONE_USER));

        TimeTable timeTable = TimeTable.builder()
                .name("DefaultTimeTable")
                .users(users)
                .status(Status.ACTIVE)
                .build();

        timeTableRepository.save(timeTable);

        return new TimeTableResponseDto(timeTable);
    }
}
