package com.example.memo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.memo.dto.MemoDTO;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService {
    private final MemoRepository memoRepository;

    @Override
    public Long create(MemoDTO dto) {
        Memo memo = dtoToEntity(dto);
        return memoRepository.save(memo).getMno();

    }

    @Override
    public MemoDTO read(Long id) {
        Memo memo = memoRepository.findById(id).get();

        return entityToDto(memo);

    }

    @Override
    public List<MemoDTO> list() {
        List<Memo> list = memoRepository.findAll();
        return list.stream().map(memo -> entityToDto(memo)).collect(Collectors.toList());

    }

    @Override
    public Long update(MemoDTO dto) {
        // Memo memo = memoRepository.findById(37L).get();
        // memo.setMemoText("memo_text_7");
        Memo memo = dtoToEntity(dto);
        return memoRepository.save(memo).getMno();
    }

    @Override
    public void delete(Long id) {
        memoRepository.deleteById(id);
    }

}
