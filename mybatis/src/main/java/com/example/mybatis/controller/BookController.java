package com.example.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mybatis.dto.BookDTO;
import com.example.mybatis.dto.CategoryDTO;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PageResultDto;
import com.example.mybatis.dto.PublisherDTO;
import com.example.mybatis.service.BookService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/book")
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto,
            Model model) {
        log.info("도서 전체 목록 요청 {}", requestDto);
        List<BookDTO> result = bookService.getList(requestDto);
        int total = bookService.getTotalCnt(requestDto);

        log.info("list {}", result);
        log.info("total {}", total);
        model.addAttribute("result", new PageResultDto<>(requestDto, total, result));

    }

    // 상세조회
    @GetMapping(value = { "/read", "/modify" })
    public void getMethodName(@RequestParam Long id, @ModelAttribute("requestDto") PageRequestDto requestDto,
            Model model) {
        log.info("도서 상세 요청 {}", id);

        BookDTO dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postModify(BookDTO dto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("도서 수정 요청 {}", dto);
        log.info("requestDto {}", requestDto);
        if (bookService.update(dto)) {
            // 상세조회로 이동
            rttr.addAttribute("id", dto.getId()); // ?id=3
            rttr.addAttribute("page", requestDto.getPage());
            rttr.addAttribute("size", requestDto.getSize());
            rttr.addAttribute("type", requestDto.getType());
            rttr.addAttribute("keyword", requestDto.getKeyword());
            return "redirect:read";
        } else {
            return "/book/modify";
        }
    }

    @PostMapping("/remove")
    public String postMethodName(@RequestParam Long id, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("도서 삭제 요청 {}", id);
        log.info("requestDto {}", requestDto);
        bookService.delete(id);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:list";
    }

    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BookDTO dto, Model model,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("도서 입력 폼 요청");

        List<CategoryDTO> categories = bookService.getCateList();
        List<PublisherDTO> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publisherDtos);
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BookDTO dto, BindingResult result, Model model,
            RedirectAttributes rttr, @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("도서 입력 요청 {}", dto);

        List<CategoryDTO> categories = bookService.getCateList();
        List<PublisherDTO> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publisherDtos);

        if (result.hasErrors()) {
            return "/book/create";
        }

        // 서비스 insert 호출
        bookService.create(dto);

        rttr.addFlashAttribute("msg", "도서가 등록되었습니다.");
        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:list";
    }

}