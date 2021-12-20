package com.example.jpabasic.service;

import com.example.jpabasic.InheritanceMapping.Movie;
import com.example.jpabasic.InheritanceMapping.repository.AlbumRepository;
import com.example.jpabasic.InheritanceMapping.repository.BookRepository;
import com.example.jpabasic.InheritanceMapping.repository.ItemInheritanceRepository;
import com.example.jpabasic.InheritanceMapping.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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


    @Test
    @Rollback(value = false)
    @Commit
    public void inheritanceJoinTest() {

        Movie movie = new Movie();
        movie.setName("sdfsdfs ");
        movie.setActor("yohan");
        movie.setPrice(1000);
        movie.setDirector("yohan");

        movieRepository.save(movie);

    }

}

