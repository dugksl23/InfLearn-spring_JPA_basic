package com.example.jpabasic.InheritanceMapping_study;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "album")
public class Album extends ItemInheritance {

    private String artist;

}
