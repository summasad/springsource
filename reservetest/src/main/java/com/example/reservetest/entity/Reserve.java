package com.example.reservetest.entity;

import com.example.reservetest.entity.reserveEnum.ReserveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = { "theater", "dMember", "dMovie", "guest" })
@Entity
public class Reserve extends BaseEntity {
    @SequenceGenerator(name = "reserve_seq_gen", sequenceName = "reserve_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserve_seq_gen")
    @Id
    private Long reserveNo; // 예매번호

    private Long price; // 영화금액

    @Enumerated(EnumType.STRING)
    private ReserveStatus status; // 예매상태

    @ManyToOne(fetch = FetchType.LAZY)
    private Theater theater; // 상영관 정보

    @ManyToOne(fetch = FetchType.LAZY)
    private DMember dMember; // 회원 정보

    @ManyToOne(fetch = FetchType.LAZY)
    private DMovie dMovie; // 영화 정보

    @OneToOne(fetch = FetchType.LAZY)
    private Guest guest; // 비회원 예매

}
