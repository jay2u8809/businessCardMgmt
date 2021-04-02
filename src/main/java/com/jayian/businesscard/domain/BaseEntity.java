package com.jayian.businesscard.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @CreatedDate
    @Column(name = "create_dt", length = 50, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_dt", length = 50)
    private LocalDateTime modifiedDate;

    @PrePersist
    public void preInsert() {

        LocalDateTime now = LocalDateTime.now();
        if (this.createdDate == null) {
            this.createdDate = now;
        }
        if (this.modifiedDate == null) {
            this.modifiedDate = now;
        }
    }

    @PreUpdate
    public void preUpdate() {

        LocalDateTime now = LocalDateTime.now();
        this.modifiedDate = now;

        // merge
        if (this.createdDate == null) {
            this.createdDate = now;
        }
    }

}
