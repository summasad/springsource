package com.example.reservetest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@Entity
public class Theater {
    @SequenceGenerator(name = "theater_seq_gen", sequenceName = "theater_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theater_seq_gen")
    @Id
    private Long theaterNo; // 상영관번호

    private String theaterName; // 극장 이름

    private String theaterCate; // 상영관 종류

    private String showtimes; // 상영 시간

    private String seat; // 좌석

}
