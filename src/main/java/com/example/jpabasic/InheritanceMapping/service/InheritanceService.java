package com.example.jpabasic.InheritanceMapping.service;


import com.example.jpabasic.InheritanceMapping.Movie;
import com.example.jpabasic.InheritanceMapping.repository.AlbumRepository;
import com.example.jpabasic.InheritanceMapping.repository.BookRepository;
import com.example.jpabasic.InheritanceMapping.repository.ItemInheritanceRepository;
import com.example.jpabasic.InheritanceMapping.repository.MovieRepository;
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
