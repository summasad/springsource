package com.example.project1.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project1.dto.LoginDto;
import com.example.project1.dto.SampleDto;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        log.info("index.요청");

        model.addAttribute("name", "hong");
        LoginDto loginDto = new LoginDto("hong123", "hong123");
        model.addAttribute("login", loginDto);

        // SampleDto sampleDto = new SampleDto();
        // sampleDto.setId(1L);
        // sampleDto.setFirst("Adam");
        // sampleDto.setLast("Savage");
        // sampleDto.setRDateTime(LocalDateTime.now());
        SampleDto sampleDto = SampleDto.builder()
                .id(1L)
                .first("Adam")
                .last("Savage")
                .regDateTime(LocalDateTime.now())
                .build();
        model.addAttribute("sampleDto", sampleDto);

        List<SampleDto> list = new ArrayList<>();
        LongStream.rangeClosed(1, 10)
                .forEach(i -> {
                    SampleDto dto = SampleDto.builder()
                            .id(i)
                            .first("first" + i)
                            .last("last" + i)
                            .regDateTime(LocalDateTime.now())
                            .build();
                    list.add(dto);
                });
        model.addAttribute("list", list);
        model.addAttribute("now", new Date());
        model.addAttribute("price", 123456789);
        model.addAttribute("option", Arrays.asList("AAAA", "BBBB", "CCCC", "DDDD"));
        model.addAttribute("title", "This is just sample");
        return "index";
    }

    @ResponseBody
    @GetMapping("/test")
    public String getMethodName() {
        return "반갑습니다";
    }

}
