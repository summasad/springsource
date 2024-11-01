package com.example.project3.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.project3.entity.Member;
import com.example.project3.entity.Team;

@SpringBootTest
public class TeamMemberRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void createTest() {
        Team team = Team.builder()
                .id("team1")
                .name("팀1")
                .build();
        teamRepository.save(team);

        team = Team.builder()
                .id("team2")
                .name("팀2")
                .build();
        teamRepository.save(team);

    }

    @Test
    public void createMemberTest() {

        Team team1 = teamRepository.findById("team1").get();
        Team team2 = Team.builder().id("team2").build();

        IntStream.rangeClosed(1, 5).forEach(i -> {
            Member member = Member.builder()
                    .id("user" + i)
                    .userName("성춘향" + i)
                    .team(team1)
                    .build();
            memberRepository.save(member);

        });

        IntStream.rangeClosed(6, 10).forEach(i -> {
            Member member = Member.builder()
                    .id("user" + i)
                    .userName("성춘향" + i)
                    .team(team2)
                    .build();
            memberRepository.save(member);

        });

    }

    @Test
    public void selectTest() {
        // 회원 찾기

        Member member = memberRepository.findById("user1").get();
        System.out.println(member);

        // 팀 정보 찾기
        System.out.println(member.getTeam());
        // 팀명 찾기
        System.out.println(member.getTeam().getName());
    }

    @Test
    public void memberEqualTeamTest() {
        memberRepository.findByMemberEqualTeam("팀1").forEach(m -> System.out.println(m));
    }

    @Test
    public void updateTest() {
        // user6의 팀 team2로 변경하기
        Member member = memberRepository.findById("user6").get();

        Team team2 = teamRepository.findById("team2").get();

        member.setTeam(team2);
        memberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        // team1을 제거
        // join 해놓아서 오류
        // teamRepository.deleteById("team1");

        // 외래키 제약조건에서는 자식부터 삭제 / 부모부터 삽입
        // 자식의 팀 변경 or 같이 삭제
        // team1 요소를 전부 불러와서 변경
        Team team = Team.builder().id("team1").build();
        Team team2 = Team.builder().id("team2").build();
        List<Member> members = memberRepository.findByTeam(team);
        members.forEach(member -> System.out.println(member));

        members.forEach(member -> {
            member.setTeam(team2);
            memberRepository.save(member);
        });

        // team1을 제거
        teamRepository.deleteById("team1");

    }

    // member 삽입하면서 team 삽입이 가능한가?
    // sql : 1) 부모 삽입 2) 자식 삽입
    // jpa 에서는 객체로 다룸 -> 함께 가능
    @Test
    public void memberAndTeamInsertTest() {

        // EntityNotFoundException: Unable to find com.example.project3.entity.Team with
        // id team3
        // @~~~~~(cascade = CascadeType.ALL) 설정이 없는 경우

        // id team3
        Team team = Team.builder().id("team3").name("팀3").build();
        // teamRepository.save(team);
        Member member = Member.builder().id("user11").userName("홍길동").team(team).build();
        memberRepository.save(member);
    }

    @Test
    public void memberAndTeamUpdateTest() {

        Team team = teamRepository.findById("team3").get();
        team.setName("victory");
        // teamRepository.save(team); 없이도 member 삽입하면서 team 정보 수정 가능

        Member member = Member.builder().id("user11").userName("홍길동").team(team).build();
        memberRepository.save(member);
    }

    @Transactional
    @Test
    public void selectMemberTest() {
        // 팀 찾기
        Team team2 = teamRepository.findById("team2").get();
        team2.getMembers().forEach(m -> {
            // 멤버 정보 출력
            System.out.println(m);
            // 팀에 속한 멤버 이름 출력
            System.out.println(m.getUserName());

            // 오류:
            // org.hibernate.LazyInitializationException: ~~~initialize proxy - no Session
            // -> 1) fetch = FetchType.EAGER (Entity 에서 수행) : left join
            // -> 2) @Transactional(메소드 위에서 수행) : select 두번 수행

        });
    }

    @Test
    public void teamAndMemberInsertTest() {

        Team team = Team.builder().id("team3").name("팀3").build();
        // teamRepository.save(team);
        Member member = Member.builder().id("user12").userName("수선화").team(team).build();

        team.getMembers().add(member);

        teamRepository.save(team);

        // 원래 방식
        // Team team = Team.builder().id("team4").name("팀4").build();
        // teamRepository.save(team);
        // Member member =
        // Member.builder().id("user12").userName("수선화").team(team).build();
        // memberRepository.save(member);
    }
}
