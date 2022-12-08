package com.mustache.bbs1.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
// JPA의 엔티티 클래스가 상속받을 경우 자식 클래스에게 매핑 정보를 전달
@MappedSuperclass
// 엔티티를 데이터베이스에 적용하기 전후로 콜백을 요청
@EntityListeners(AuditingEntityListener.class)
@ToString
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // update 불가
    private LocalDateTime createdAt;
}
