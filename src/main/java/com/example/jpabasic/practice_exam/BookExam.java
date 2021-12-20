package com.example.jpabasic.practice_exam;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Book")
public class BookExam extends ProductExam {

    private String author;
}
