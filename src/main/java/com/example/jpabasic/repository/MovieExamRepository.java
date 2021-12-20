package com.example.jpabasic.repository;

import com.example.jpabasic.practice_exam.MovieExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieExamRepository extends JpaRepository<MovieExam, Long> {
}
