package com.example.book.controller;

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

import com.example.book.dto.BookDTO;
import com.example.book.dto.CategoryDTO;
import com.example.book.dto.PublisherDTO;
import com.example.book.service.BookService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/book")
@Controller
public class BookController {

    private final BookService bookService;

    // 리스트 추출
    @GetMapping("/list")
    public void getList(Model model) {
        log.info("도서 전체 목록 요청");

        List<BookDTO> list = bookService.getList();
        model.addAttribute("list", list);
    }

    // 상세조회
    @GetMapping(value = { "/read", "/modify" })
    public void getMethodName(@RequestParam Long id, Model model) {
        log.info("도서 상세 요청 {}", id);

        BookDTO dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postModify(BookDTO dto, RedirectAttributes rttr) {
        log.info("도서 수정 요청 {}", dto);

        Long id = bookService.update(dto);

        // 상세조회로 이동
        rttr.addAttribute("id", id); // ?id=3
        return "redirect:read";
    }

    @PostMapping("/remove")
    public String postMethodName(@RequestParam Long id) {
        log.info("도서 삭제 요청 {}", id);
        bookService.delete(id);

        return "redirect:list";
    }

    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BookDTO dto, Model model) {
        log.info("도서 입력 폼 요청");

        List<CategoryDTO> categories = bookService.getCateList();
        List<PublisherDTO> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publisherDtos);
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BookDTO dto, BindingResult result, Model model,
            RedirectAttributes rttr) {
        log.info("도서 입력 요청 {}", dto);

        List<CategoryDTO> categories = bookService.getCateList();
        List<PublisherDTO> publisherDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publisherDtos);

        if (result.hasErrors()) {
            return "/book/create";
        }

        // 서비스 insert 호출
        Long id = bookService.create(dto);

        rttr.addFlashAttribute("msg", id + "번 도서가 등록되었습니다.");
        return "redirect:list";
    }

}