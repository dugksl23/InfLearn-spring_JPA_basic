package com.example.jpabasic.practice_exam;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Movie")
public class MovieExam extends ProductExam {

    private String director;
    private String author;
    private String actor;

}
