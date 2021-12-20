package com.example.jpabasic.InheritanceMapping.core;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createdDate;

}
