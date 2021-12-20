package com.example.jpabasic;

import com.example.jpabasic.InheritanceMapping.repository.AlbumRepository;
import com.example.jpabasic.InheritanceMapping.repository.BookRepository;
import com.example.jpabasic.InheritanceMapping.repository.ItemInheritanceRepository;
import com.example.jpabasic.InheritanceMapping.repository.MovieRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;

@SpringBootTest
class JpaBasicApplicationTests {


    @Test
    void contextLoads() {
    }

}
