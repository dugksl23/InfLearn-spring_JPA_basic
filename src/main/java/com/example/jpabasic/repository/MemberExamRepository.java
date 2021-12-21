package com.example.jpabasic.repository;

import com.example.jpabasic.practice_exam.MemberExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberExamRepository extends JpaRepository<MemberExam, Long> {

}
