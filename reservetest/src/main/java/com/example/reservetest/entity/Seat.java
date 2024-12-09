package com.example.reservetest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Seat {
    @Id
    private Long seatId;

    private String theaterCate; // 상영관 종류

    private String seatTitle; // 좌석정보

    private String price; // 가격
}
