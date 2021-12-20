package com.example.jpabasic.InheritanceMapping_study.repository;

import com.example.jpabasic.InheritanceMapping_study.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
