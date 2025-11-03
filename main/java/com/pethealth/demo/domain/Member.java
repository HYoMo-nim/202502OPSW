package com.pethealth.demo.domain; // 본인 패키지 경로

import java.time.LocalDateTime; // timestamp는 LocalDateTime으로 받는 것이 좋습니다.

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // JPA는 기본 생성자가 필요합니다.
@Entity // 이 클래스가 DB 테이블과 매핑됨
@Table(name = "member") // 'member' 테이블과 연결
public class Member {

    @Id // Primary Key
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, // PostgreSQL은 SEQUENCE를 사용합니다.
        generator = "member_user_id_seq" // schema.sql에 정의된 시퀀스 이름
    )
    @SequenceGenerator(
        name = "member_user_id_seq",
        sequenceName = "member_user_id_seq",
        allocationSize = 1 // 시퀀스 증가 값
    )
    @Column(name = "user_id") // DB 컬럼명과 Java 필드명이 다를 때 매핑
    private Long userId;

    @Column(unique = true, nullable = false) // 제약 조건 추가
    private String email;

    @Column(nullable = false)
    private String password;

    private String nickname; // DB 컬럼명과 필드명이 같으면 @Column 생략 가능

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}