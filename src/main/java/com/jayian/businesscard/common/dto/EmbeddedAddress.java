package com.jayian.businesscard.common.dto;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class EmbeddedAddress {

    private String countryCode;

    private String zipCode;

    private String address1;

    private String address2;

    private String address3;

    private String address4;

    public EmbeddedAddress(String countryCode, String zipCode, String address1, String address2, String address3, String address4) {
        this.countryCode = countryCode;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
    }
}
