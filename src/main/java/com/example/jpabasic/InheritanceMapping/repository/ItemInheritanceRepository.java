package com.example.jpabasic.InheritanceMapping.repository;

import com.example.jpabasic.InheritanceMapping.ItemInheritance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInheritanceRepository extends JpaRepository<ItemInheritance, Long> {
}
