package com.example.inuphonebook.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Department department;

    private String name;

    private String homepage;

    private String location;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
}
