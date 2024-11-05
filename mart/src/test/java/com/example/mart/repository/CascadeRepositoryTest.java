package com.example.mart.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.mart.entity.cascade.Child;
import com.example.mart.entity.cascade.Parent;
import com.example.mart.repository.cascade.ChildRepository;
import com.example.mart.repository.cascade.ParentRepository;

@SpringBootTest
public class CascadeRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    // C
    // @Test
    // public void parentInsertTest() {
    // parentRepository.save(Parent.builder().name("parent1").build());
    // }

    // @Test
    // public void childInsertTest() {
    // childRepository.save(Child.builder().name("child1").build());
    // childRepository.save(Child.builder().name("child2").build());
    // childRepository.save(Child.builder().name("child3").build());
    // }

    // @Test
    // public void cascadeTest() {
    // Parent parent = parentRepository.findById(1L).get();
    // childRepository.findAll().forEach(child -> {
    // child.setParent(parent);
    // childRepository.save(child);
    // System.out.println(child);
    // });
    // }

    @Test
    public void testParentInsert() {
        Parent parent = Parent.builder().name("parent3").build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Child child = Child.builder().name("child" + i).parent(parent).build();
            // parent.getChilds().add(child); // child 정보는 db에 추가되지 않음
            childRepository.save(child);
        });
        // parentRepository.save(parent);
    }

    @Test
    public void testParentInsert2() {
        Parent parent = Parent.builder().name("parent3").build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Child child = Child.builder().name("child" + i).parent(parent).build();
            parent.getChilds().add(child); // child 정보는 db에 추가되지 않음
        });
        parentRepository.save(parent);
    }

    @Test
    public void testChildRead() {
        // 자식2 정보 조회(+ 부모조회)
        Child child = childRepository.findById(2L).get();
        System.out.println(child);
        System.out.println(child.getParent());
    }

    @Test
    public void testParentDelete() {
        // 부모 삭제시 관련되어 있는 자식 같이 삭제
        // 자식 삭제 코드를 작성하지 않고

        parentRepository.deleteById(3L);
    }

    @Commit
    @Transactional
    @Test
    public void testParentDelete2() {
        // 부모 삭제시 관련되어 있는 자식 같이 삭제
        // 자식 삭제 코드를 작성하지 않고
        Parent parent = parentRepository.findById(1L).get();

        // 부모를 이용해 자식만 삭제
        parent.getChilds().remove(0);
        // parent.getChilds().remove(1);
        // parent.getChilds().remove(2);
        parentRepository.save(parent);

    }

}
