package com.example.jpabasic.exam1;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class MemberExam {
    @Id
    @Column(name = "memberExam_id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<OrderExam> orders;

}
