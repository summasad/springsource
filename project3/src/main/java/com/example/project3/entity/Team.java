package com.example.project3.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "members") // 셋팅은 제외하고 시작하기
@Entity
public class Team {

    @Id
    private String id;

    private String name;

    // @OneToMany 는 left join을 하지 않음 => member 정보 없음
    // 1) fetch 지정해서 해결 @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    // 2) 메소드 가서 @Transactional
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL) // 양쪽에 @설정 => 일방통행 통로 2개 설정, 꼭 주인이 누구인지 표시할것
    @Builder.Default // list 들어올때 쓸것
    private List<Member> members = new ArrayList<>();
}
