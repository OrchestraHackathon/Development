package com.example.server.repository;

import com.example.server.domain.TimeTable;
import com.example.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    Optional<TimeTable> findByUsersId(Long UsersId);
}
