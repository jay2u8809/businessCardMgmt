package com.jayian.businesscard.domain.member;

import com.jayian.businesscard.common.code.UseYN;
import com.jayian.businesscard.common.code.YN;
import com.jayian.businesscard.common.dto.EmbeddedAddress;
import com.jayian.businesscard.common.dto.EmbeddedName;
import com.jayian.businesscard.common.dto.EmbeddedTel;
import com.jayian.businesscard.domain.BaseEntity;
import com.jayian.businesscard.domain.businesscard.BusinessCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Table(name="member")
@Entity
public class Member extends BaseEntity {

    @OneToMany(mappedBy = "member")
    @OrderBy("business_card_sn desc")
    private List<BusinessCard> businessCards = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_sn", nullable = false, precision = 15, scale = 0)
    private Long memberSn;

    @Column(name = "member_id", nullable = false, length = 100)
    private String memberId;

    @Column(name = "member_password", nullable = false, length = 200)
    private String memberPassword;

    @Embedded
    private EmbeddedName memberName = new EmbeddedName();

    @Column(name = "member_email", nullable = false, length = 50)
    private String memberEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "email_verify_yn", length = 1)
    private YN emailVerifyYn = YN.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "withdrawal_yn", length = 1)
    private UseYN withdrawalYn = UseYN.N;

    @Embedded
    private EmbeddedAddress memberAddress = new EmbeddedAddress();

    @Embedded
    private EmbeddedTel memberTel = new EmbeddedTel();
}
