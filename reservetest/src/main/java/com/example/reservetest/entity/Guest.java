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
    private Long guestNo;
    private String password;
    private String guestName;
    private String guestPhone;
    private String guestBirth;

    @OneToOne
    private Reserve reserve;

}
