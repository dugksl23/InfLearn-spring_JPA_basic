package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.BaseEntity;
import com.example.jpabasic.practice_exam.core.embedded.Address;
import com.example.jpabasic.practice_exam.core.embedded.LogDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MemberExam extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "memberExam_id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<OrderExam> orders;

    @Embedded
    private LogDate logDate;

    @Embedded
    private Address homeAddress;

}
