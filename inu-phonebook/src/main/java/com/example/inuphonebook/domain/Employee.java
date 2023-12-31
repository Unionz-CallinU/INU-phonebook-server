package com.example.inuphonebook.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String college;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String name;

    private String position;

    @Column(length = 500)
    private String role;

    @Column(nullable = false)
    private String phoneNumber;

    private String email;

    @Lob
    private String imageUrl;

    public void setImageByCrawling(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Builder
    public Employee(String college, String department, String name, String position, String role, String phoneNumber, String email, String imageUrl) {
        this.college = college;
        this.department = department;
        this.name = name;
        this.position = position;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageUrl = imageUrl;
    }
}
