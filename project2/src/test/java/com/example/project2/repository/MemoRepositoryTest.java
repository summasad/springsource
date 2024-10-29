package com.example.project2.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Memo;

// 단위 테스트 클래스

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    // CRUD
    // C(insert)
    @Test
    public void insertTest() {

        LongStream.rangeClosed(1, 10).forEach(i -> {

            Memo memo = Memo.builder().memoText("Memo Text...." + i).build();
            System.out.println(memoRepository.save(memo));

        });

    }

    // R(Read)
    @Test
    public void selectOneTest() {
        Optional<Memo> result = memoRepository.findById(5L);

        Memo memo = result.get();
        System.out.println(memo);
    }

    @Test
    public void selectAllTest() {
        List<Memo> list = memoRepository.findAll();

        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    // U(update)
    @Test
    public void updateTest() {
        Optional<Memo> result = memoRepository.findById(5L);

        result.ifPresent(memo -> {
            memo.setMemoText("Update Title...");
            System.out.println(memoRepository.save(memo));

        });
    }

    // D(delete)
    @Test
    public void deleteTest() {
        // Optional<Memo> result = memoRepository.findById(10L);

        // result.ifPresent(memo -> {
        // memoRepository.delete(memo);

        // });
        memoRepository.deleteById(3L);
    }

}
