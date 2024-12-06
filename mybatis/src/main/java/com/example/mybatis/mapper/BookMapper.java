package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatis.dto.BookDTO;
import com.example.mybatis.dto.CategoryDTO;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PublisherDTO;

@Mapper
public interface BookMapper {
    public BookDTO read(Long id);

    public List<BookDTO> listAll(PageRequestDto requestDto);

    public int totalCnt(PageRequestDto requestDto);

    public int update(BookDTO dto);

    public int delete(Long id);

    public List<PublisherDTO> publishers();

    public List<CategoryDTO> categories();

    public int create(BookDTO dto);
}
