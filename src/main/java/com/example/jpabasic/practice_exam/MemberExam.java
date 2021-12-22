package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.BaseEntity;
import com.example.jpabasic.practice_exam.core.embedded.Address;
import com.example.jpabasic.practice_exam.core.embedded.LogDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipcode")),
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))})
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "work_zipcode")),
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street"))})
    private Address workAddress;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "food", joinColumns = @JoinColumn(name = "memberExam_id"))
    // @OneToMany와 같은 1:다의 관계, food 테이블의 관계의 주인
    // 반면 food table @ManyToOne(mappedBy="member_id")으로 관계의 주인이지만,
    // @JoinColumn(name = "member_id", , insertable = false, updatable = false)
    // 이건 마치 1:다의 @OneToMany 에서 관계의 주인은 One이지만, db에서는 다 쪽에 PK가 가기에
    // 다쪽이 되는 테이블에서 @JoinColumn(insertable = false, updatable = false) 와 비슷
    // 1:다의 관계에서 다에서는 관계의 주인을 나타내는 mappedBy는 one에서 정의하기에 one이 주인일 때에는
    // mappedBy 속성 사용할 수 없는 것과 현재 이 상황이 유사
    private Set<String> favoriteFood = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "member_id"))
    private List<Address> addresses = new ArrayList<>();
}
