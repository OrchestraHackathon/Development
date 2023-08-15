package com.example.server.domain.users;

import com.example.server.domain.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Users extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private String role;

    // 연관 관계 Mapping
    @OneToMany(mappedBy = "users")
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<TimeTable> timeTables = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Course> courses = new ArrayList<>();
}
