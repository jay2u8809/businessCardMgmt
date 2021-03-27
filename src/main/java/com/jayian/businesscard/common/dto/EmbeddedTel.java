package com.jayian.businesscard.common.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class EmbeddedTel {

    @Column(name = "tel_1", nullable = false, length = 30)
    private String tel1;

    @Column(name = "tel_2", nullable = false, length = 30)
    private String tel2;

    @Column(name = "tel_3", nullable = false, length = 30)
    private String tel3;

    public EmbeddedTel(String tel1, String tel2, String tel3) {
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
    }
}
