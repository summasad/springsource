package com.example.reservetest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/login")
    public void getLogin() {
        log.info("login 요청");

    }

    @GetMapping("/register")
    public void getRegister() {
        log.info("login 요청");

    }
}
