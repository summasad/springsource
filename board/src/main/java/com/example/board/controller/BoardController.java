package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("전체 목록 {}", requestDto);
        PageResultDto<BoardDto, Object[]> result = boardService.getList(requestDto);
        System.out.println(result);
        model.addAttribute("result", result);
    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(@RequestParam Long bno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("상세조회 {}", bno);
        BoardDto dto = boardService.read(bno);
        model.addAttribute("dto", dto);
    }

    // 로그인 사용자 == 작성자

    @PreAuthorize("authentication.name == #dto.writerEmail")
    @PostMapping("/modify")
    public String postMethodName(BoardDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("수정요청 {}", dto);
        Long bno = boardService.update(dto);
        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:read";
    }

    @PreAuthorize("authentication.name == #writerEmail")
    @PostMapping("/remove")
    public String postRemove(@RequestParam Long bno, String writerEmail,
            @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("삭제요청 {}", bno);
        boardService.remove(bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/board/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BoardDto dto,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("등록폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BoardDto dto, BindingResult result,
            @ModelAttribute("requestDto") PageRequestDto requestDto, RedirectAttributes rttr) {
        log.info("등록 요청 {}", dto);
        if (result.hasErrors()) {
            return "/board/create";
        }
        // service
        Long bno = boardService.register(dto);
        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/board/read";
    }

}
