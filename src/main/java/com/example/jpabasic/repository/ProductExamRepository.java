package com.example.jpabasic.repository;


import com.example.jpabasic.basic_study.Item;
import com.example.jpabasic.practice_exam.ProductExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductExamRepository extends JpaRepository<ProductExam, Long>{

}
