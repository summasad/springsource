package com.example.reservetest.entity;

import groovy.transform.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(excludes = "reserve")
@Entity
public class Guest extends BaseEntity {

    @Id
    private Long guestNo; // 비회원번호

    private String password; // 비밀번호

    private String guestName; // 비회원이름

    private String guestPhone; // 비회원 전화번호

    private String guestBirth; // 비회원 생년월일

    @OneToOne(mappedBy = "guest")
    private Reserve reserve; // 예매

}
