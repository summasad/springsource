package com.example.project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project3.entity.Member;
// 관계 설정
// -left join (기본 설정)
// -inner join 하고 싶을때
// -> @Query(" ") : sql 쿼리의 형식과 유사한 JPA Query 로 작성
import com.example.project3.entity.Team;

public interface MemberRepository extends JpaRepository<Member, String> {

    // from 절에 Entity 이름 써야 함(대소문자 구별함)
    @Query("SELECT m FROM Member m JOIN m.team t WHERE t.name = :name")
    List<Member> findByMemberEqualTeam(String name);

    List<Member> findByTeam(Team team);

}
