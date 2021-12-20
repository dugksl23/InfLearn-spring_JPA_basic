package com.example.jpabasic.exam1;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CategoryExam {
    @Id
    @GeneratedValue
    @Column(name = "category_id", nullable = false)
    private Long id;

    // 셀프 조인
//    @ManyToOne
//    @JoinColumn(name = "parent_id")
//    private CategoryExam parent;
//
//    @OneToMany(mappedBy = "parent")
//    private List<CategoryExam> child = new ArrayList<>();

}
