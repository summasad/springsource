package com.example.reservetest.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/movie")
public class MovieController {

    @GetMapping("/main")
    public void getHome() {
        log.info("home 폼 요청");

    }

    @GetMapping("/reservation")
    public void getReservation() {
        log.info("home 폼 요청");

    }

    @GetMapping("/center")
    public void getCenter() {
        log.info("home 폼 요청");

    }

    @GetMapping("/read")
    public void getRead() {
        log.info("home 폼 요청");

    }

}
