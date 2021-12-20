package com.example.jpabasic.InheritanceMapping.repository;

import com.example.jpabasic.InheritanceMapping.Book;
import com.example.jpabasic.InheritanceMapping.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
