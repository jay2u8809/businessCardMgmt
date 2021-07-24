package com.jayian.businesscard.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @CreatedBy
    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_dt", length = 50, updatable = false)
    private LocalDateTime createdDt;

    @LastModifiedBy
    @Column(name = "modified_by", length = 50)
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_dt", length = 50)
    private LocalDateTime modifiedDt;

    @PrePersist
    public void preInsert() {

        // JPA insert 하기 전 실행됨
        if (ObjectUtils.isEmpty(this.createdBy)) {
            this.createdBy = "admin";
        }
        if (ObjectUtils.isEmpty(this.modifiedBy)) {
            this.modifiedBy = "admin";
        }

        LocalDateTime now = LocalDateTime.now();
        if (this.createdDt == null) {
            this.createdDt = now;
        }
        if (this.modifiedDt == null) {
            this.modifiedDt = now;
        }
    }

    @PreUpdate
    public void preUpdate() {

        this.modifiedBy = "admin";

        LocalDateTime now = LocalDateTime.now();
        this.modifiedDt = now;

        // merge
        if (this.createdBy == null) {
            this.createdBy = "admin";
        }
        if (this.createdDt == null) {
            this.createdDt = now;
        }
    }
}
