package com.jayian.businesscard.common.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class EmbeddedName {

    @Column(name = "given_name", nullable = false, length = 50)
    private String givenName;

    @Column(name = "sur_name", nullable = false, length = 50)
    private String surName;

    @Column(name = "given_name_en", nullable = false, length = 50)
    private String givenNameEn;

    @Column(name = "sur_name_en", nullable = false, length = 50)
    private String surNameEn;

    public EmbeddedName (String givenName, String surName, String givenNameEn, String surNameEn) {
        this.givenName = givenName;
        this.surName = surName;
        this.givenNameEn = givenNameEn;
        this.surNameEn = surNameEn;
    }
}
