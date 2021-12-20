package com.example.jpabasic.repository;

import com.example.jpabasic.basic_study.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {


}
