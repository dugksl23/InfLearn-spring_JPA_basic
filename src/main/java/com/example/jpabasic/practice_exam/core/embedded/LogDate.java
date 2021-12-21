package com.example.jpabasic.practice_exam.core.embedded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class LogDate {


    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startWorkDate;


    @PrePersist
    protected void onCreate() {
        if (this.lastLogDate == null) {
            this.lastLogDate = new Date();
            this.startWorkDate = new Date();
        }

    }

    public void isWork(Date localDateTime) {
        // .. 값 타입을 위한 메서드를 정의할 수 있다
        this.startWorkDate = localDateTime;
    }

}
