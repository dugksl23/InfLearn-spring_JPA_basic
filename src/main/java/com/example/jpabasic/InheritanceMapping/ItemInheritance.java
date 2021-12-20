package com.example.jpabasic.InheritanceMapping;


import com.example.jpabasic.InheritanceMapping.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorColumn(name = "D_TYPE")
public class ItemInheritance extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "itemInheritance_id", nullable = false)
    private Long id;

    private String name;
    private Integer price;

}
