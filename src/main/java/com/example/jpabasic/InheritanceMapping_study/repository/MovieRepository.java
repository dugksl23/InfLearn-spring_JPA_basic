package com.example.jpabasic.InheritanceMapping_study.repository;

import com.example.jpabasic.InheritanceMapping_study.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
