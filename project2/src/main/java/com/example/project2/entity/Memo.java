package com.example.project2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//entity 패키지명 하단에 작성하는 클래스는 테이블 정의하는 것과 동일함
//memo 테이블 생성
//메모번호(mno), 메모내용(memo_text)
//@Entity(name=""): 이 클래스는 테이블과 연동되어 있음 의미 /  @Id : PK(반드시 필요)
// @Column(name="memo_text", length=10, nullable=false)
// 테이블명 = 클래스 명, 필드명 = 컬럼명 동일하게 생성
// => 이름 변경하고 싶다면 name
// not null => nullable=false
// 오라클 시퀀스 :
// - @SequenceGenerator(name = "제너레이터명", sequenceName = "시퀀스명", allocationSize = 1)
// - 클래스 위나 혹은 pk 컬럼명 위에 작성
// - pk 컬럼명 위에 추가 속성 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "제너레이터명")

// 날짜 칼럼 어노테이션
// @CreatedDate : 삽입시 현재 시스템 날짜와 시각 자동 삽입
// @LastModifiedDate : 수정시 현재 시스템 날짜와 시각 자동 삽입

// Long => number(19,0) String => varchar2(255) int => number(10,0)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "memo_seq_gen", sequenceName = "memo_seq", allocationSize = 1)
@Entity
public class Memo {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memo_seq_gen")
    @Id
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
