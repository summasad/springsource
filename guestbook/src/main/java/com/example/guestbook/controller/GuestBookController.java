package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/guestbook")
@Controller
public class GuestBookController {
    private final GuestBookService guestBookService;

    @GetMapping("/list")
    public void getList(PageRequestDto requestDto, Model model) {
        log.info("전체 목록");
        PageResultDto<GuestBookDto, GuestBook> result = guestBookService.list(requestDto);
        model.addAttribute("result", result);
    }

}
