package com.example.jpabasic.InheritanceMapping_study.repository;

import com.example.jpabasic.InheritanceMapping_study.ItemInheritance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInheritanceRepository extends JpaRepository<ItemInheritance, Long> {
}
