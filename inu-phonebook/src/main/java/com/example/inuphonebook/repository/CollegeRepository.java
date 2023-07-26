package com.example.inuphonebook.repository;

import com.example.inuphonebook.domain.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
}
