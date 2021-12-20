package com.example.jpabasic.InheritanceMapping;

import com.example.jpabasic.InheritanceMapping.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "movie")
public class Movie extends ItemInheritance {

    private String director;
    private String actor;

}
