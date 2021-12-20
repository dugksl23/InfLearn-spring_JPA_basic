package com.example.jpabasic.InheritanceMapping;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorColumn(name = "D_TYPE")
public class ItemInheritance {
    @Id
    @GeneratedValue
    @Column(name = "itemInheri_id", nullable = false)
    private Long id;

    private String name;
    private Integer price;

    @CreationTimestamp
    private LocalDateTime createdDate;

}
