package com.example.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mybatis.dto.BookDTO;
import com.example.mybatis.dto.CategoryDTO;
import com.example.mybatis.dto.PageRequestDto;
import com.example.mybatis.dto.PublisherDTO;
import com.example.mybatis.mapper.BookMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;

    @Override
    public boolean create(BookDTO dto) {
        return bookMapper.create(dto) == 1 ? true : false;
    }

    @Override
    public BookDTO getRow(Long id) {
        return bookMapper.read(id);
    }

    @Override
    public List<BookDTO> getList(PageRequestDto requestDto) {
        return bookMapper.listAll(requestDto);
    }

    @Override
    public boolean update(BookDTO dto) {
        return bookMapper.update(dto) == 1 ? true : false;
    }

    @Override
    public boolean delete(Long id) {
        return bookMapper.delete(id) == 1 ? true : false;
    }

    @Override
    public List<CategoryDTO> getCateList() {
        return bookMapper.categories();
    }

    @Override
    public List<PublisherDTO> getPubList() {
        return bookMapper.publishers();
    }

    @Override
    public int getTotalCnt(PageRequestDto requestDto) {
        return bookMapper.totalCnt(requestDto);
    }

}
