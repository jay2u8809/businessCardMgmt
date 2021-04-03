package com.jayian.businesscard.domain.businesscard;

import com.jayian.businesscard.common.code.UseYN;
import com.jayian.businesscard.common.code.YN;
import com.jayian.businesscard.common.dto.EmbeddedAddress;
import com.jayian.businesscard.common.dto.EmbeddedName;
import com.jayian.businesscard.common.dto.EmbeddedTel;
import com.jayian.businesscard.domain.BaseEntity;
import com.jayian.businesscard.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name="business_card")
@Entity
public class BusinessCard extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_sn", referencedColumnName = "member_sn", nullable = false)
    private Member member;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_card_sn", nullable = false, precision = 14, scale = 0)
    private Long businessCardSn;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "memberName", column = @Column(name = "member_name")) })
    private EmbeddedName memberName;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "company_job", length = 100)
    private String companyJob;

    @Column(name = "company_department", length = 100)
    private String companyDepartment;

    @Column(name = "email_address", nullable = false, length = 50)
    private String emailAddress;

    @Column(name = "memo", length = 200)
    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(name = "rep_yn", nullable = false, length = 1)
    private YN repYn = YN.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", nullable = false, length = 1)
    private UseYN useYn = UseYN.Y;

    @Enumerated(EnumType.STRING)
    @Column(name = "own_businesscard_yn", nullable = false, length = 1)
    private YN ownBusinessCardYn = YN.N;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 999;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "phoneNo", column = @Column(name = "phone_no")) })
    private EmbeddedTel phoneNo = new EmbeddedTel();

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "phoneNo", column = @Column(name = "fax_no")) })
    private EmbeddedTel faxNo = new EmbeddedTel();

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "repAddress", column = @Column(name = "rep_address")) })
    private EmbeddedAddress repAddress = new EmbeddedAddress();

}
