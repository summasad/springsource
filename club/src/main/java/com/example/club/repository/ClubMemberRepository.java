package com.example.club.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.example.club.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

    // where cm1_0.club_member_email=? and cm1_0.club_member_from_social=?
    @EntityGraph(attributePaths = { "roles" }, type = EntityGraphType.LOAD)
    Optional<ClubMember> findByEmailAndFromSocial(String email, boolean fromSocial);
}

// @EntityGraph
// fetch 속성이 Lazy인 경우 특정 기능에서만 EAGER로 동작하도록 설정
// attributePaths 에 표시된 속성만 EAGER로 처리
