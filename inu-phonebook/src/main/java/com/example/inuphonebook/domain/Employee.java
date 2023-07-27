package com.example.inuphonebook.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Column(nullable = false)
    private String name;

    private String position;

    private String role;

    @Column(nullable = false)
    private String phoneNumber;

    private String email;

}
