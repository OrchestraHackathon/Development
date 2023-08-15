package com.example.server.domain.users;

import com.example.server.domain.*;
import jakarta.persistence.*;
import lombok.*;
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

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String name;

    // 연관 관계 Mapping
    @OneToMany(mappedBy = "users")
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<TimeTable> timeTables = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Course> courses = new ArrayList<>();

    @Builder
    public Users(String email, String name, String nickname, String password, Status status, String role) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.status = status;
        this.role = role;
    }
}
