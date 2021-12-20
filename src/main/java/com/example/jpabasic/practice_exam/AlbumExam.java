package com.example.jpabasic.practice_exam;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "Album")
public class AlbumExam extends ProductExam {

    private String artist;

}
