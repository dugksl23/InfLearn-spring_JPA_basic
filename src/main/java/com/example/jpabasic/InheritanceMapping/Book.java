package com.example.jpabasic.InheritanceMapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "book")
public class Book extends ItemInheritance {

    private String isbn;
    private String author;

}
