package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/guestbook")
@Controller
public class GuestBookController {
    private final GuestBookService service;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("전체 목록");
        PageResultDto<GuestBookDto, GuestBook> result = service.list(requestDto);
        System.out.println(result);
        model.addAttribute("result", result);
    }

    @GetMapping({ "/read", "/modify" })
    public void getRow(@RequestParam Long gno, PageRequestDto requestDto, Model model) {
        log.info("상세조회 {}", gno);
        GuestBookDto dto = service.read(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postModify(GuestBookDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        // 수정 완료 후 상세조회
        log.info("수정요청 {}", dto);
        Long gno = service.update(dto);

        rttr.addAttribute("gno", gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:read";

    }

    @PostMapping("/remove")
    public String postRemove(@RequestParam Long gno, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("삭제 요청 {}", gno);
        service.delete(gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:list";
    }

}
