package com.example.jpabasic.InheritanceMapping_study.service;


import com.example.jpabasic.InheritanceMapping_study.Movie;
import com.example.jpabasic.InheritanceMapping_study.repository.AlbumRepository;
import com.example.jpabasic.InheritanceMapping_study.repository.BookRepository;
import com.example.jpabasic.InheritanceMapping_study.repository.ItemInheritanceRepository;
import com.example.jpabasic.InheritanceMapping_study.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InheritanceService {

    private final MovieRepository movieRepository;
    private final AlbumRepository albumRepository;
    private final BookRepository bookRepository;
    private final ItemInheritanceRepository itemInheritanceRepository;

    public void insertTest() {

        Movie movie = new Movie();
        movie.setName("movie");
        movie.setActor("yohan");
        movie.setPrice(1000);
        movie.setDirector("yohan");

        movieRepository.save(movie);
    }


}
