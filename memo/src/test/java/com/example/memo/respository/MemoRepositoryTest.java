package com.example.memo.respository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testMempInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("memo" + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testMemoRead() {
        // 6번 메모 가져오기
        Memo memo = memoRepository.findById(36L).get();
        System.out.println(memo);
        // 전체 메모 가져오기
        List<Memo> list = memoRepository.findAll();
        list.forEach(m -> System.out.println(m));
    }

    @Test
    public void testMemoUpdate() {
        // 7번 메모 수정
        Memo memo = memoRepository.findById(37L).get();
        memo.setMemoText("memo_text_7");
        memoRepository.save(memo);
    }

    @Test
    public void testMemoDelete() {
        // 10번 메모 삭제
        memoRepository.deleteById(40L);
    }

}
