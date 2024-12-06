package com.example.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mybatis.dto.PageRequestDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {
    @GetMapping("/")
    public String getHome(@ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("홈 요청");

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "index";
    }

}
