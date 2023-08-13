package com.example.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Friend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 친구의 이름
    @Column(nullable = false)
    private String name;
    // 친구의 닉네임
    @Column(nullable = false)
    private String nickname;
    // 친구 여부
    @Column(nullable = false)
    private Boolean areWeFriend;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
