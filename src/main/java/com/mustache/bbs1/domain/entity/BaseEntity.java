package com.mustache.bbs1.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //entity를 DB에 적용 전후로 콜백(auditing 정보를 주입하는 클래스)
@Getter
@ToString
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @Getter(value = AccessLevel.PROTECTED)
    @Column(columnDefinition = "datetime null default null")
    private LocalDateTime deletedAt;

    public void deleteSoftly() {
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return null != deletedAt;
    }

    public void undoDeletion() {
        this.deletedAt = null;
    }
}
