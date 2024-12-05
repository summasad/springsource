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
    public Long create(BookDTO dto) {
        // return bookRepository.save(dtoToEntity(dto)).getId();
        return null;
    }

    @Override
    public BookDTO getRow(Long id) {
        return null;
    }

    @Override
    public List<BookDTO> getList(PageRequestDto requestDto) {
        return bookMapper.listAll(requestDto);
    }

    @Override
    public Long update(BookDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<CategoryDTO> getCateList() {
        return null;
    }

    @Override
    public List<PublisherDTO> getPubList() {
        return null;
    }

    @Override
    public int getTotalCnt(PageRequestDto requestDto) {
        return bookMapper.totalCnt(requestDto);
    }

}
