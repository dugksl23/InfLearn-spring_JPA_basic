package com.example.jpabasic.repository;

import com.example.jpabasic.basic_study.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
