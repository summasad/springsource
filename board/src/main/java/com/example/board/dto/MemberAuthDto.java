package com.example.board.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Builder
@ToString
@Setter
@Getter
public class MemberAuthDto extends User {
    private MemberDto memberDto;

    // 리스트 생성 방법
    // 1)
    // List<String> list = new ArrayList<>();
    // list.add();
    // 2)
    // private List<String> list = List.of("spring","java","sdk");
    // 3)
    // List<String> list = Arrays.asList("spring","java","sdk");
    public MemberAuthDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MemberAuthDto(MemberDto memberDto) {
        this(memberDto.getEmail(), memberDto.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + memberDto.getRole().toString())));
        this.memberDto = memberDto;
    }
}
