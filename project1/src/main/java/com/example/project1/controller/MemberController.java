package com.example.project1.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.dto.LoginDto;
import com.example.project1.dto.MemberDto;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/member")

public class MemberController {
    @GetMapping("/login")
    public void getLogin() {
        log.info("login 페이지 요청");
    }

    // 1)
    // @PostMapping("/login")
    // public void postLogin(HttpServletRequest request) {
    // log.info("login 요청 - 사용자 입력값 요청");

    // String userid = request.getParameter("userid");
    // String password = request.getParameter("password");

    // log.info("userid : {}, password {}", userid, password);
    // }

    // 2)
    // 변수명=input의 name
    // @PostMapping("/login")
    // public void postLogin(String userid, String password) {
    // log.info("login 요청 - 사용자 입력값 요청");
    // log.info("userid : {}, password {}", userid, password);
    // }

    // 3)
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("login") LoginDto loginDto) {
        log.info("login 요청 - 사용자 입력값 요청");
        log.info("userid : {}, password {}", loginDto.getUserid(), loginDto.getPassword());
        return "index";
    }

    @GetMapping("/register")
    public void getRegister() {
        log.info("register 폼 요청");
    }

    @PostMapping("/register")
    public String postRegister(MemberDto memberDto) {
        log.info("회원가입 요청 {}", memberDto);
        // log.info("userid : {}, password: {}, name: {}", memberDto.getUserid(),
        // memberDto.getPassword(),
        // memberDto.getName());

        return "redirect:/member/login"; // redirect: 위에서 설정한 경로 의미, html파일(x)
    }

    // http://localhost:8080/path1 + get
    @GetMapping("/path1")
    public String method1() {
        return "login";
    }

    // http://localhost:8080/path2 + post
    @PostMapping("/path2")
    public void method2() {
        // 1.입력값 가져오기
        // 2.service 호출 후 결과받기
        // 3.model.addAttribute()
        // 4.페이지 이동
    }

    @GetMapping("/path3")
    public String method3() {
        return "redirect: /login"; // http://localhost:8080/login
    }

}
