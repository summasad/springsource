package com.example.guestbook.service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;

public interface GuestBookService {

    Long register(GuestBookDto dto);

    GuestBookDto read(Long gno);

    PageResultDto<GuestBookDto, GuestBook> list(PageRequestDto requestDto);

    Long update(GuestBookDto dto);

    void delete(Long gno);

    // dtoToEntity
    public default GuestBook dtoToEntity(GuestBookDto dto) {
        return GuestBook.builder().gno(dto.getGno()).writer(dto.getWriter()).title(dto.getTitle())
                .content(dto.getContent()).build();
    }

    // entityToDto
    public default GuestBookDto entityToDto(GuestBook entity) {
        return GuestBookDto.builder().gno(entity.getGno()).writer(entity.getWriter()).title(entity.getTitle())
                .content(entity.getContent()).regDate(entity.getRegDate()).updateDate(entity.getUpdateDate()).build();
    }
}
