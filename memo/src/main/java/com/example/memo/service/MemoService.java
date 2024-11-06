package com.example.memo.service;

import java.util.List;

import com.example.memo.dto.MemoDTO;
import com.example.memo.entity.Memo;

public interface MemoService {
    // crud 메소드
    Long create(MemoDTO dto);

    MemoDTO read(Long id);

    List<MemoDTO> list();

    Long update(MemoDTO dto);

    void delete(Long id);

    // dto ==> entity
    public default Memo dtoToEntity(MemoDTO dto) {
        return Memo.builder()
                .mno(dto.getMno())
                .memoText(dto.getMemoText())
                .build();

    }

    // entity ==> dto
    public default MemoDTO entityToDto(Memo memo) {
        return MemoDTO.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .createdDateTime(memo.getCreatedDateTime())
                .lastModifDateTime(memo.getLastModifDateTime())
                .build();

    }
}
