package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.dto.BookDTO;
import com.example.mybatis.dto.CategoryDTO;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PublisherDTO;

public interface BookService {
    // crud
    boolean create(BookDTO dto);

    BookDTO getRow(Long id);

    List<BookDTO> getList(PageRequestDto requestDto);

    int getTotalCnt(PageRequestDto requestDto);

    boolean update(BookDTO dto);

    boolean delete(Long id);

    List<CategoryDTO> getCateList();

    List<PublisherDTO> getPubList();

}
