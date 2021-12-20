package com.example.jpabasic.InheritanceMapping;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorColumn(name = "D_TYPE")
public class ItemInheritance {
    @Id
    @GeneratedValue
    @Column(name = "itemInheritance_id", nullable = false)
    private Long id;

    private String name;
    private Integer price;

    @CreationTimestamp
    private LocalDateTime createdDate;

}
