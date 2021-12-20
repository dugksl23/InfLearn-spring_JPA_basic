package com.example.jpabasic.service;

import com.example.jpabasic.InheritanceMapping_study.Movie;
import com.example.jpabasic.InheritanceMapping_study.repository.AlbumRepository;
import com.example.jpabasic.InheritanceMapping_study.repository.BookRepository;
import com.example.jpabasic.InheritanceMapping_study.repository.ItemInheritanceRepository;
import com.example.jpabasic.InheritanceMapping_study.repository.MovieRepository;
import com.example.jpabasic.practice_exam.MovieExam;
import com.example.jpabasic.practice_exam.ProductExam;
import com.example.jpabasic.repository.MovieExamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)// junit5
//@DataJpaTest
public class InheritanceServiceTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ItemInheritanceRepository itemInheritanceRepository;

    @Autowired
    MovieExamRepository movieExamRepository;


    @Test
    @Rollback(value = false)
    @Commit
    public void inheritanceJoinTest() {

        MovieExam movie = new MovieExam();
        movie.setActor("요한");
        movie.setAuthor("나");
        movie.setDirector("Yohan");


        movieExamRepository.save(movie);

    }

}

