package com.example.jpabasic.practice_exam;

import com.example.jpabasic.practice_exam.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CategoryExam extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id", nullable = false)
    private Long id;

    // ์ํ ์กฐ์ธ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryExam parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<CategoryExam> child = new ArrayList<>();

}
