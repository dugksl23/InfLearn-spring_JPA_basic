package com.example.jpabasic.InheritanceMapping_study.repository;

import com.example.jpabasic.InheritanceMapping_study.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
