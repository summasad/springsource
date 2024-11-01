package com.example.project3.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@ToString(exclude = "team") // default 배제, member만 - 원할때 getTeam
@Table(name = "team_member")
@Entity // Entity를 @Query 사용할 떄 여기서 지정한 이름으로 접근해야 함
public class Member {

    @Id
    private String id;

    private String userName;

    // 관계
    // 관계의 주도권 : @ManyToOne 를 설정한 entity 가 주인
    @ManyToOne(cascade = CascadeType.ALL) // 다이어그램에서 n대1
    private Team team; // 외래키

}
