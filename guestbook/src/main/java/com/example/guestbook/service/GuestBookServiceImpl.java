package com.example.guestbook.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.repository.GuestBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class GuestBookServiceImpl implements GuestBookService {
    private final GuestBookRepository guestBookRepository;

    @Override
    public Long register(GuestBookDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public GuestBookDto read(Long gno) {
        // GuestBook result = guestBookRepository.findById(gno).get();
        // GuestBookDto dto = entityToDto(result);
        // return dto;
        return entityToDto(guestBookRepository.findById(gno).get());
    }

    @Override
    public PageResultDto<GuestBookDto, GuestBook> list(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());

        // Predicate predicate(BooleanBuilder 사용), Pageable pageable
        Page<GuestBook> result = guestBookRepository.findAll(
                guestBookRepository.makePredicate(requestDto.getType(), requestDto.getKeyword()),
                pageable);
        Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public Long update(GuestBookDto dto) {
        // dto->entity
        GuestBook entity = guestBookRepository.findById(dto.getGno()).get();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return guestBookRepository.save(entity).getGno();
    }

    @Override
    public void delete(Long gno) {
        guestBookRepository.deleteById(gno);
    }

}
