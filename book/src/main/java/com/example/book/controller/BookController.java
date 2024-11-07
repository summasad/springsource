package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/book")
@Controller
public class BookController {
    private final BookService bookService;

    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BookDTO dto, Model model) {
        log.info("도서 입력 폼 요청");
        List<CategoryDTO> categories = bookService.getCateList();
        List<PublisherDTO> publishers = bookService.getPubList();
        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publishers);

    }

    @PostMapping("/create")
    public String posCreate(@Valid @ModelAttribute("dto") BookDTO dto, BindingResult result, Model model,
            RedirectAttributes rttr) {
        log.info("도서 정보 입력 요청 {}", dto);

        List<CategoryDTO> categories = bookService.getCateList();
        List<PublisherDTO> publishers = bookService.getPubList();
        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publishers);

        if (result.hasErrors()) {
            return "/book/create";
        }
        // 서비스 insert 호출
        Long id = bookService.create(dto);
        rttr.addFlashAttribute("msg", id + "번 도서가 등록되었습니다.");
        return "rediret:list";
    }

    // 리스트 추출
    @GetMapping("/list")
    public void getList(Model model) {
        log.info("도서 전체 목록 요청");
        List<BookDTO> list = bookService.getList();
        model.addAttribute("list", list);

    }

    // 상세조회
    @GetMapping(value = { "/read", "/modify" })
    public void getRead(@RequestParam Long id, Model model) {
        log.info("도서 상세 조회");
        BookDTO dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postUpdate(BookDTO dto, RedirectAttributes rttr) {
        log.info("도서 가격 수정 {}", dto);
        Long id = bookService.update(dto);
        rttr.addAttribute("id", id); // ?id=3 주소줄에 딸려가기

        return "rediret:read";
    }

    @PostMapping("/remove")
    public String postDelete(@RequestParam Long id) {
        log.info("도서 삭제 {}", id);
        bookService.delete(id);

        return "rediret:list";
    }

}
